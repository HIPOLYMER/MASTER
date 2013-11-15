import java.io.File;
import java.math.BigInteger;
import java.util.List;
import java.util.Vector;

import org.docx4j.jaxb.Context;
import org.docx4j.model.structure.SectionWrapper;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.FooterPart;
import org.docx4j.openpackaging.parts.WordprocessingML.HeaderPart;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.BooleanDefaultTrue;
import org.docx4j.wml.Br;
import org.docx4j.wml.CTColumns;
import org.docx4j.wml.CTFrame.Sz;
import org.docx4j.wml.FldChar;
import org.docx4j.wml.FooterReference;
import org.docx4j.wml.Ftr;
import org.docx4j.wml.Hdr;
import org.docx4j.wml.HdrFtrRef;
import org.docx4j.wml.HeaderReference;
import org.docx4j.wml.HpsMeasure;
import org.docx4j.wml.Jc;
import org.docx4j.wml.JcEnumeration;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.PPr;
import org.docx4j.wml.R;
import org.docx4j.wml.RFonts;
import org.docx4j.wml.RPr;
import org.docx4j.wml.STBrType;
import org.docx4j.wml.STFldCharType;
import org.docx4j.wml.SectPr;
import org.docx4j.wml.Text;

public class MakeWord {
	private static WordprocessingMLPackage wordMLPackage = null;
	private static ObjectFactory factory;
	private static Relationship relationship;
	private static String path;
	private static Vector<Vector<String>> problemHeader;
	private static Vector<Vector<String>> problemBody;
	private String indicatePic=null;	//this indicate picture
	//constuctor
	public MakeWord(Vector<Vector<String>> header, Vector<Vector<String>> body, String path) throws Docx4JException{

		try {
			wordMLPackage = WordprocessingMLPackage.createPackage();
			this.path=path;
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		factory = Context.getWmlObjectFactory();
		relationship=null;
		this.path = path;
		this.problemHeader=header;
		this.problemBody=body;
		this.indicatePic="<??>";
	}

	//word file formatting method total 3 method
	//1. footer
	//content insert into word file as a footer
	public void WriteFooter(String content, char align) throws InvalidFormatException{
		relationship = createFooterPart(content, align);
		createFooterReference(relationship);
	}

	private static Relationship createFooterPart(String content, char align) throws InvalidFormatException{
		FooterPart footerPart = new FooterPart();
		footerPart.setPackage(wordMLPackage);

		footerPart.setJaxbElement(createFooter(content, align));

		return wordMLPackage.getMainDocumentPart().addTargetPart(footerPart);
	}

	private static Ftr createFooter(String content, char align){
		
		Ftr footer = factory.createFtr();
		P paragraph = factory.createP();
		R run = factory.createR();
		Text text = new Text();
		text.setValue(content);
		run.getContent().add(text);
		paragraph.getContent().add(run);
		paragraph = AlignParagraph(paragraph, align);
		footer.getContent().add(paragraph);
		return footer;
	}

	private static void createFooterReference(Relationship relationship){
		List<SectionWrapper> sections = wordMLPackage.getDocumentModel().getSections();

		SectPr sectionProperties = sections.get(sections.size()-1).getSectPr();

		//There is always a section wrapper, but it might not contain a sectPr
		if(sectionProperties==null){
			sectionProperties = factory.createSectPr();
			wordMLPackage.getMainDocumentPart().addObject(sectionProperties);
			sections.get(sections.size()-1).setSectPr(sectionProperties);
		}

		FooterReference footerReference = factory.createFooterReference();
		footerReference.setId(relationship.getId());
		footerReference.setType(HdrFtrRef.DEFAULT);
		sectionProperties.getEGHdrFtrReferences().add(footerReference);
	}

	//2. pagenumber in footer
	public void WritePagenumberFooter(char align) throws InvalidFormatException{
		Relationship relationship = createFooterPart(align);
		createFooterReference(relationship);
	}

	private static Relationship createFooterPart(char align) throws InvalidFormatException{
		FooterPart footerPart = new FooterPart();
		footerPart.setPackage(wordMLPackage);

		footerPart.setJaxbElement(createFooterWithPageNr(align));

		return wordMLPackage.getMainDocumentPart().addTargetPart(footerPart);
	}

	private static Ftr createFooterWithPageNr(char align){
		Ftr ftr = factory.createFtr();
		P paragraph = factory.createP();

		addFieldBegin(paragraph);
		addPageNumberField(paragraph);
		addFieldEnd(paragraph);
		AlignParagraph(paragraph, align);
		ftr.getContent().add(paragraph);
		return ftr;
	}

	private static void addPageNumberField(P paragraph){
		R run = factory.createR();
		Text txt = new Text();
		txt.setSpace("preserve");
		txt.setValue(" PAGE \\* MERGEFORMAT ");
		
		
		RPr rpr = factory.createRPr();
		RFonts rfonts=factory.createRFonts();
		rfonts.setHAnsi("나눔고딕");
		rfonts.setAscii("나눔고딕");
		rfonts.setEastAsia("나눔고딕");	//elementary style change
		rpr.setRFonts(rfonts);
		
		HpsMeasure sz= factory.createHpsMeasure();
		BigInteger fs=new BigInteger("16");
		sz.setVal(fs);
		rpr.setSz(sz);
		run.getContent().add(factory.createRInstrText(txt));
		run.setRPr(rpr);
		paragraph.getContent().add(run);
	}

	private static void addFieldBegin(P paragraph){
		R run = factory.createR();
		FldChar fldchar = factory.createFldChar();
		fldchar.setFldCharType(STFldCharType.BEGIN);
		run.getContent().add(fldchar);
		paragraph.getContent().add(run);
	}

	private static void addFieldEnd(P paragraph){
		FldChar fldcharend = factory.createFldChar();
		fldcharend.setFldCharType(STFldCharType.END);
		R run = factory.createR();
		run.getContent().add(fldcharend);
		paragraph.getContent().add(run);
	}

	//3. header
	//content insert into word file as a header
	public void WriteHeader(String content, char align) throws InvalidFormatException{
		relationship = createHeaderPart(content, align);
		createHeaderReference(relationship);
	}

	private static Relationship createHeaderPart(String content, char align) throws InvalidFormatException{
		HeaderPart HeaderPart = new HeaderPart();
		HeaderPart.setPackage(wordMLPackage);

		HeaderPart.setJaxbElement(createHeader(content, align));

		return wordMLPackage.getMainDocumentPart().addTargetPart(HeaderPart);
	}

	private static Hdr createHeader(String content, char align){
		Hdr header = factory.createHdr();
		P paragraph = factory.createP();
		R run = factory.createR();
		Text text = new Text();
		text.setValue(content);
		run.getContent().add(text);
		paragraph.getContent().add(run);
		paragraph = AlignParagraph(paragraph, align);
		header.getContent().add(paragraph);
		return header;
	}

	private static void createHeaderReference(Relationship relationship){
		List<SectionWrapper> sections = wordMLPackage.getDocumentModel().getSections();

		SectPr sectionProperties = sections.get(sections.size()-1).getSectPr();

		//There is always a section wrapper, but it might not contain a sectPr
		if(sectionProperties==null){
			sectionProperties = factory.createSectPr();
			wordMLPackage.getMainDocumentPart().addObject(sectionProperties);
			sections.get(sections.size()-1).setSectPr(sectionProperties);
		}

		HeaderReference headerReference = factory.createHeaderReference();
		headerReference.setId(relationship.getId());
		headerReference.setType(HdrFtrRef.DEFAULT);
		sectionProperties.getEGHdrFtrReferences().add(headerReference);
	}

	//4. columns
	//number indicates columns number
	public void createColumns(String number){
		List<SectionWrapper> sections = wordMLPackage.getDocumentModel().getSections();

		SectPr sectionProperties = sections.get(sections.size()-1).getSectPr();

		//There is always a section wrapper, but it might not contain a sectPr
		if(sectionProperties==null){
			sectionProperties = factory.createSectPr();
			wordMLPackage.getMainDocumentPart().addObject(sectionProperties);
			sections.get(sections.size()-1).setSectPr(sectionProperties);
		}

		CTColumns ctcol = new CTColumns();
		BigInteger colnum = new BigInteger(number);

		ctcol.setNum(colnum);
		sectionProperties.setCols(ctcol);
	}

	//5. save file
	public void saveFile(){
		try {
			wordMLPackage.save(new File(path));
		} catch (Docx4JException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//6. write title
	public void writeTitle(String content){
		wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Title", content);
	}


	//this method returns boolean value indicate that whether parameter has Image separate character(indicatePic)
	private boolean HasTextImg(String text){
		int IsPicture=0;
		Boolean PicInSub=false;

		if(text.length()>this.indicatePic.length()){	//compare with picture indicate character
			String PicFind = text.substring(0, this.indicatePic.length());
			IsPicture=PicFind.compareTo(this.indicatePic); 	//IsPicture == 0 �좎럥占쏙옙占썲윜諛몄굡占쎌빢�쇿뜝�숈삕�좎룞�쇿뜝�숈삕�좎룞�쇿뜝占�
			if(IsPicture==0){	//picture is in the text
				PicInSub=true;
			}
		}

		return PicInSub;
	}

	private void WriteTextWithImg(String content, char align, String size, boolean isbold){
		int ir=content.indexOf(this.indicatePic);
		if(ir==0){ //before picture there is no string
			String after=content.substring(ir);

			String imgPath=getImagePath(after, indicatePic, ir);

			try {
				this.InsertImg(imgPath, "0", "0");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int beginindex=getAfterIndex(after, indicatePic, ir);
			if(after.length()==beginindex){	//after picture there is no string

			}
			else
				this.writeText(after.substring(beginindex), align, size, isbold);
		}
		else{
			String prev=content.substring(0, ir-1);	//well
			String after=content.substring(ir);

			this.writeText(prev, align, size, isbold);
			String imgPath=getImagePath(after, indicatePic, ir);
			try {
				this.InsertImg(imgPath, "0", "0");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int beginindex=getAfterIndex(after, indicatePic, ir);
			if(after.length()==beginindex){ //after picture there is no string

			}
			else
				this.writeText(after.substring(beginindex), align, size, isbold);
		}
	}

	//7. write body
	public void writeProblem(boolean keyword, boolean solution, char align){
		int j=0;
		String pro_ans=null;
		for(j=0; j<problemBody.size(); j++){
			if(j==0){
				this.writeBr(5);
				continue;
			}
			else{
				Vector<String> item = this.problemBody.get(j);
				Integer proNum = new Integer(j);
				for(int i=0; i<item.size()-1; i++)	//last memember is problem ID
				{
					String text=item.get(i);
					String remain=null;
					String prsize="20";
					String exsize="18";
					boolean isprobold=true;
					boolean isexabold=false;
					boolean imgIn=HasTextImg(text);

					if(imgIn){
						remain=text.substring(this.indicatePic.length());
					}
					else{
						remain=new String(text);
					}

					switch(i){
					case 0:
						this.writeText(proNum.toString() + ". " + remain, align, prsize, isprobold);
						break;
					case 1:{
						if(imgIn){
							WriteTextWithImg(remain, align, exsize, isexabold);
						}
						else
							this.writeText(remain, align, exsize, isexabold);
						break;
					}
					case 2:{
						if(imgIn){
							WriteTextWithImg("가. "+remain, align, exsize, isexabold);
						}
						else
							this.writeText("가. "+remain, align, exsize, isexabold);
						break;
					}
					case 3:{
						if(imgIn){
							WriteTextWithImg("나. "+remain, align, exsize, isexabold);
						}
						else
							this.writeText("나."+remain, align, exsize, isexabold);
						break;
					}
					case 4:{
						if(imgIn){
							WriteTextWithImg("다."+remain, align, exsize, isexabold);
						}
						else
							this.writeText("다."+remain, align, exsize, isexabold);
						break;
					}
					case 5:{
						if(imgIn){
							WriteTextWithImg("라."+remain, align, exsize, isexabold);
						}
						else
							this.writeText("라."+remain, align, exsize, isexabold);
						break;
					}
					case 6:{
						if(!keyword){	//keyword checkbox is not selected
							continue;
						}
						if(imgIn){
							WriteTextWithImg(remain, align, exsize, isexabold);
						}
						else
							this.writeText(remain, align, exsize, isexabold);
						break;
					}
					case 7:{
						if(!solution){	//full solution is not selected
							continue;
						}
						if(imgIn){
							WriteTextWithImg(remain, align, exsize, isexabold);
						}
						else
							this.writeText(remain, align, exsize, isexabold);
						break;
					}
					case 8:{	//answer index
						
						switch(Integer.parseInt(text)){
						case 1:
							text = "가";
							break;
						case 2:
							text = "나";
							break;
						case 3:
							text = "다";
							break;
						case 4:
							text = "라";
							break;
						}
						
						pro_ans = Integer.toString(j)+" - " + text + " || ";
					}
					}
				}
			}
			if(j==problemBody.size()-1){	//last problem we didn't want blank in the paper.
				this.writeBr(5);
				this.writeText(pro_ans, 'l', "18", false);
			}
			else
				this.writeBr(5);	//one problem is printed. To separate from previous one.
		}

	}

	private String getImagePath(String remain, String sep, int startindex){
		String imgpath="";
		String content = remain.substring(sep.length());
		int i=0;

		for(i=0; i<content.length(); i++){
			char p=content.charAt(i);

			if(	p==' ' ||
					p=='\n' ||
					p=='\t'){
				break;
			}
			else
				imgpath+=p;

		}

		return imgpath;
	}

	private int getAfterIndex(String remain, String sep, int startindex){
		String content = remain.substring(sep.length());
		int i=0;

		for(i=0; i<content.length(); i++){
			char c=content.charAt(i);
			if(	c==' ' ||
					c=='\n' ||
					c=='\t')
				break;
		}

		return i+sep.length();
	}

	public void writeText(String content, char align, String size, boolean bold){
		P paragraph = factory.createP();
		R run = factory.createR();
		
		RPr rpr = factory.createRPr();
		RFonts rfonts=factory.createRFonts();
		rfonts.setHAnsi("나눔고딕");
		rfonts.setAscii("나눔고딕");
		rfonts.setEastAsia("나눔고딕");	//elementary style change
		rpr.setRFonts(rfonts);
		
		HpsMeasure sz= factory.createHpsMeasure();
		BigInteger fs=new BigInteger(size);
		sz.setVal(fs);
		rpr.setSz(sz);
		
		BooleanDefaultTrue isbold = factory.createBooleanDefaultTrue();
		isbold.setVal(bold);
		rpr.setB(isbold);
		
		Text con = factory.createText();
		con.setValue(content);
		run.getContent().add(con);
		run.setRPr(rpr);
		paragraph.getContent().add(run);
		paragraph = AlignParagraph(paragraph, align);
		wordMLPackage.getMainDocumentPart().addObject(paragraph);
	}

	//8. write new line with parameter num, indicates how many new lines
	public void writeBr(int num){
		P paragraph = factory.createP();
		R run = factory.createR();

		Br br = factory.createBr();

		for(int i=0; i<num; i++){
			run.getContent().add(br);
		}

		paragraph.getContent().add(run);

		wordMLPackage.getMainDocumentPart().addObject(paragraph);
	}

	//9. insert picture
	//when we see unique character that indicate picture, we use this method to insert word file
	public void InsertImg(String path, String width, String height) throws Exception{
		InsertImage img = new InsertImage();
		img.setFile(path);
		img.Insert(wordMLPackage, width, height);
	}

	//10. page breaker
	public void PageBreak(){
		MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();

		Br breakObj = new Br();
		breakObj.setType(STBrType.PAGE);

		P paragraph = factory.createP();
		paragraph.getContent().add(breakObj);
		documentPart.getJaxbElement().getBody().getContent().add(paragraph);
	}

	//11. align paragraph
	private static P AlignParagraph(P paragraph, char align){
		PPr otherProperties = factory.createPPr();
		Jc jc = factory.createJc();

		switch(align){
		case 'l':
			jc.setVal(JcEnumeration.LEFT);
			break;
		case 'r':
			jc.setVal(JcEnumeration.RIGHT);
			break;
		case 'c':
			jc.setVal(JcEnumeration.CENTER);
			break;
		case 'b':
			jc.setVal(JcEnumeration.BOTH);
			break;
		default:
			break;
		}

		otherProperties.setJc(jc);
		paragraph.setPPr(otherProperties);

		return paragraph;
	}
}
