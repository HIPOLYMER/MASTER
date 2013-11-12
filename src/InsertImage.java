import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.wml.Drawing;
import org.docx4j.wml.Jc;
import org.docx4j.wml.JcEnumeration;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.PPr;
import org.docx4j.wml.R;

public class InsertImage {
	private static ObjectFactory factory=null;
	private static File file=null;
	private static byte[] bytes=null;

	public InsertImage(){
		factory = Context.getWmlObjectFactory();
	}

	public void setFile(String path) throws IOException{
		this.file = new File(path);
		this.bytes = convertImageToByteArray(file);
	}

	public void Insert(WordprocessingMLPackage wordMLPackage, String cx, String cy) throws Exception{
		addImageToPackage(wordMLPackage, cx, cy);
	}

	//현재 지정한 크기로 이미지 넣기 가능
	private static void addImageToPackage(WordprocessingMLPackage wordMLPackage, String cx, String cy) throws Exception{
		BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);

		int docPrId = 0;
		int cNvPrId = 1;

		Inline inline = null;

		long cxl = Long.parseLong(cx); 
		long cyl = Long.parseLong(cy);

		if (cxl==0 || cyl==0) 
		{ 
			inline = imagePart.createImageInline( "filenameHint", "altText", docPrId, cNvPrId, false); 
		} 
		else 
		{ 
			inline = imagePart.createImageInline( "filenameHint", "altText", docPrId, cNvPrId, cxl, cyl, false); 
		}

		P paragraph = addInlineImageToParagraph(inline);

		wordMLPackage.getMainDocumentPart().addObject(paragraph);
	}

	private static P addInlineImageToParagraph(Inline inline){
		P paragraph = factory.createP();
		R run = factory.createR();

		Drawing drawing = factory.createDrawing();
		run.getContent().add(drawing);
		paragraph.getContent().add(run);

		PPr otherProperties = factory.createPPr();
		Jc jc = factory.createJc();
		jc.setVal(JcEnumeration.LEFT);
		otherProperties.setJc(jc);
		paragraph.setPPr(otherProperties);

		drawing.getAnchorOrInline().add(inline);

		return paragraph;
	}

	private static byte[] convertImageToByteArray(File file) throws IOException{
		InputStream is = new FileInputStream(file);
		long length = file.length();
		if(length > Integer.MAX_VALUE){
			System.out.println("File too large");
		}
		byte[] bytes = new byte[(int)length];
		int offset = 0;
		int numRead = 0;
		while(offset<bytes.length && (numRead = is.read(bytes, offset, bytes.length-offset)) >= 0){
			offset += numRead;
		}
		if(offset < bytes.length){
			System.out.println("Could not completely read file" + file.getName());
		}
		is.close();
		return bytes;
	}

}
