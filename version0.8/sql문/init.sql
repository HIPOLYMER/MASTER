
/* SAMPLE INPUT*/
INSERT INTO basicoption1(year,serial) VALUES ("2006","1");
INSERT INTO basicoption1(year,serial) VALUES ("2006","2");
INSERT INTO basicoption1(year,serial) VALUES ("2006","3");
INSERT INTO basicoption1(year,serial) VALUES ("2006","4");
INSERT INTO basicoption1(year,serial) VALUES ("2007","1");
INSERT INTO basicoption1(year,serial) VALUES ("2007","2");
INSERT INTO basicoption1(year,serial) VALUES ("2007","3");
INSERT INTO basicoption1(year,serial) VALUES ("2007","4");
INSERT INTO basicoption1(year,serial) VALUES ("2008","1");
INSERT INTO basicoption1(year,serial) VALUES ("2008","2");
INSERT INTO basicoption1(year,serial) VALUES ("2008","3");
INSERT INTO basicoption1(year,serial) VALUES ("2008","4");
INSERT INTO basicoption1(year,serial) VALUES ("2009","1");
INSERT INTO basicoption1(year,serial) VALUES ("2009","2");
INSERT INTO basicoption1(year,serial) VALUES ("2009","3");
INSERT INTO basicoption1(year,serial) VALUES ("2009","4");
INSERT INTO basicoption1(year,serial) VALUES ("2010","1");
INSERT INTO basicoption1(year,serial) VALUES ("2010","2");
INSERT INTO basicoption1(year,serial) VALUES ("2010","3");
INSERT INTO basicoption1(year,serial) VALUES ("2010","4");
INSERT INTO basicoption1(year,serial) VALUES ("2011","1");
INSERT INTO basicoption1(year,serial) VALUES ("2011","2");
INSERT INTO basicoption1(year,serial) VALUES ("2011","3");
INSERT INTO basicoption1(year,serial) VALUES ("2011","4");
INSERT INTO basicoption1(year,serial) VALUES ("2012","1");
INSERT INTO basicoption1(year,serial) VALUES ("2012","2");
INSERT INTO basicoption1(year,serial) VALUES ("2012","3");
INSERT INTO basicoption1(year,serial) VALUES ("2012","4");
INSERT INTO basicoption1(year,serial) VALUES ("2013","1");
INSERT INTO basicoption1(year,serial) VALUES ("2013","2");




INSERT INTO basicoption2(type,subject) VALUES ("A��","���ð�ȹ��");
INSERT INTO basicoption2(type,subject) VALUES ("B��","���ð�ȹ��");
INSERT INTO basicoption2(type,subject) VALUES ("A��","���ü��� �� ������ȹ");
INSERT INTO basicoption2(type,subject) VALUES ("B��","���ü��� �� ������ȹ");
INSERT INTO basicoption2(type,subject) VALUES ("A��","���ð��߷�");
INSERT INTO basicoption2(type,subject) VALUES ("B��","���ð��߷�");
INSERT INTO basicoption2(type,subject) VALUES ("A��","���� �� ������ȸ");
INSERT INTO basicoption2(type,subject) VALUES ("B��","���� �� ������ȸ");
INSERT INTO basicoption2(type,subject) VALUES ("A��","���ð�ȹ ���� ����");
INSERT INTO basicoption2(type,subject) VALUES ("B��","���ð�ȹ ���� ����");
INSERT INTO basicoption2(type,subject) VALUES ("A��","���ð�ȹ �ǹ�");
INSERT INTO basicoption2(type,subject) VALUES ("B��","���ð�ȹ �ǹ�");



-- 1���� ���ð�ȹ��
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","1. ������ ���� �� ���ùߴ�","1. ���ÿ� ���ù���","1. ������ ����� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","1. ������ ���� �� ���ùߴ�","1. ���ÿ� ���ù���","2. ������ �������");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","1. ������ ���� �� ���ùߴ�","1. ���ÿ� ���ù���","3. ����ȭ�� ���ù���");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","1. ������ ���� �� ���ùߴ�","1. ���ÿ� ���ù���","4. ������ ���� �з�");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","1. ������ ���� �� ���ùߴ�","2. ���ñ��ü��� ��������","1. ���ñ��ü��");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","1. ������ ���� �� ���ùߴ�","2. ���ñ��ü��� ��������","2. ���ð��������� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","1. ������ ���� �� ���ùߴ�","2. ���ñ��ü��� ��������","3. ���ð������� �̷�");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","1. ������ ���� �� ���ùߴ�","3. ������ �ߴ�","1. ������ ����� ��뵵��");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","1. ������ ���� �� ���ùߴ�","3. ������ �ߴ�","2. �߼�����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","1. ������ ���� �� ���ùߴ�","3. ������ �ߴ�","3. �ټ�����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","1. ������ ���� �� ���ùߴ�","3. ������ �ߴ�","4. ���뵵��");

INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","2. ���ð�ȹ �̷а� ü��","1. ���ð�ȹ�� ����� �̷�","1. ���ð�ȹ�� �ʿ伺�� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","2. ���ð�ȹ �̷а� ü��","1. ���ð�ȹ�� ����� �̷�","2. ���ð�ȹ�� ������ �ֿ� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","2. ���ð�ȹ �̷а� ü��","1. ���ð�ȹ�� ����� �̷�","3. ���ð�ȹ�̷а� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","2. ���ð�ȹ �̷а� ü��","2. ������ȹü��","1. ������ȹ�� Ư���� ��ȹü��");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","2. ���ð�ȹ �̷а� ü��","2. ������ȹü��","2. �츮������ ������ȹü��");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","2. ���ð�ȹ �̷а� ü��","3. ���ð�ȹ ���� ����","1. ���ð�ȹ ���ù� ü��");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","2. ���ð�ȹ �̷а� ü��","3. ���ð�ȹ ���� ����","2. ���ð�ȹ ���������� ��õ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","2. ���ð�ȹ �̷а� ü��","3. ���ð�ȹ ���� ����","3. ���ð�ȹ ���� ü��� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","2. ���ð�ȹ �̷а� ü��","3. ���ð�ȹ ���� ����","4. �ܱ��� ���ð�ȹ����");

INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","3. ��������м��� ��ȹ��ǥ","1. ��������","1. ���������� ���ǿ� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","3. ��������м��� ��ȹ��ǥ","1. ��������","2. ���������� ������ ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","3. ��������м��� ��ȹ��ǥ","1. ��������","3. �������� �� �м� ���");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","3. ��������м��� ��ȹ��ǥ","1. ��������","4. �����ڷ��� ������ ǥ��");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","3. ��������м��� ��ȹ��ǥ","2. GIS","1. GIS�� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","3. ��������м��� ��ȹ��ǥ","2. GIS","2. GIS�� ��ɰ� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","3. ��������м��� ��ȹ��ǥ","2. GIS","3. GIS�� Ȱ��");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","3. ��������м��� ��ȹ��ǥ","3. ��ȹ��ǥ ����","1. �α���ǥ ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","3. ��������м��� ��ȹ��ǥ","3. ��ȹ��ǥ ����","2. ��ȸ������ǥ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","3. ��������м��� ��ȹ��ǥ","3. ��ȹ��ǥ ����","3. ��Ȱȯ����ǥ");

INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","4. �ι�����ȹ","1. �����̿��ȹ","1. �����̿��ȹ�� ���� ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","4. �ι�����ȹ","1. �����̿��ȹ","2. ������ ��������");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","4. �ι�����ȹ","1. �����̿��ȹ","3. ���俹��");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","4. �ι�����ȹ","1. �����̿��ȹ","4. �������");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","4. �ι�����ȹ","1. �����̿��ȹ","5. �����̿��ȹ�� ���");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","4. �ι�����ȹ","2. �����ȹ","1. ���ñ����� Ư��");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","4. �ι�����ȹ","2. �����ȹ","2. �����ȹ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","4. �ι�����ȹ","2. �����ȹ","3. ���俹��");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","4. �ι�����ȹ","2. �����ȹ","4. ���ð��ΰ�ȹ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","4. �ι�����ȹ","2. �����ȹ","5. �������� �����ھ���");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","4. �ι�����ȹ","3. ���� �ü���ȹ","1. ��ݽü��� ���� ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","4. �ι�����ȹ","3. ���� �ü���ȹ","2. ��ݽü��� Ư���� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","4. �ι�����ȹ","3. ���� �ü���ȹ","3. ��������");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","4. �ι�����ȹ","4. ����������ȹ","1. ���������� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","4. �ι�����ȹ","4. ����������ȹ","2. ������ ������ ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","4. �ι�����ȹ","4. ����������ȹ","3. ��������������ȹ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","4. �ι�����ȹ","4. ����������ȹ","4. ģȯ���� ����������ȹ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","4. �ι�����ȹ","5. �����ȹ","1. ����� ����� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","4. �ι�����ȹ","5. �����ȹ","2. ����� ������ҿ� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","4. �ι�����ȹ","5. �����ȹ","3. �����ȹ�� ����� ���");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","4. �ι�����ȹ","5. �����ȹ","4. �����������");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","4. �ι�����ȹ","6. ȯ���ȹ","1. ���ÿ� ȯ��");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","4. �ι�����ȹ","6. ȯ���ȹ","2. ���û��°�");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","4. �ι�����ȹ","6. ȯ���ȹ","3. ���Ӱ����� ���ð���");

INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","5. ���ð�ȹ�� ����","1. �����̿��ȹ�� ����","1. �����̿��ȹ�� �������");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","5. ���ð�ȹ�� ����","1. �����̿��ȹ�� ����","2. ����������");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","5. ���ð�ȹ�� ����","2. ���ð�ȹ����� ����","1. ���ð��߻���� ����� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","5. ���ð�ȹ�� ����","2. ���ð�ȹ����� ����","2. ������������ ����� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","5. ���ð�ȹ�� ����","2. ���ð�ȹ����� ����","3. ���ð�ȹ�ü������ ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","5. ���ð�ȹ�� ����","2. ���ð�ȹ����� ����","4. ���ð�ȹ ������ ���� ������ȹ�� ��ȹ ��ü��");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","5. ���ð�ȹ�� ����","3. ���ð�ȹ�� �پ��� ����","1. ���������(New Urbanism)");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","5. ���ð�ȹ�� ����","3. ���ð�ȹ�� �پ��� ����","2. ESSD�� Eco-city");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","5. ���ð�ȹ�� ����","3. ���ð�ȹ�� �پ��� ����","3. �׸���Ƽ(Green City)");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","5. ���ð�ȹ�� ����","3. ���ð�ȹ�� �پ��� ����","4. ����Ʈ��Ƽ(Smart City)");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","5. ���ð�ȹ�� ����","3. ���ð�ȹ�� �پ��� ����","5. Compact City");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","5. ���ð�ȹ�� ����","3. ���ð�ȹ�� �پ��� ����","6. U-city");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","5. ���ð�ȹ�� ����","3. ���ð�ȹ�� �پ��� ����","7. ��Ÿ");

INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","6. ���ð����� ���ð�ȹ�� �̷� ����","1. ���ð���","1. ���ð����� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","6. ���ð����� ���ð�ȹ�� �̷� ����","1. ���ð���","2. ���������� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","6. ���ð����� ���ð�ȹ�� �̷� ����","1. ���ð���","3. �ֹ������� �Ź��ͽ�");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","6. ���ð����� ���ð�ȹ�� �̷� ����","1. ���ð���","4. ���ü������");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","6. ���ð����� ���ð�ȹ�� �̷� ����","2. ���ð�ȹ�� �̷������� ����","1. ������ ��ȭ�� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð�ȹ��","6. ���ð����� ���ð�ȹ�� �̷� ����","2. ���ð�ȹ�� �̷������� ����","2. �̷����ð�ȹ�� ����");


--2 ���� ���ü��� �� ������ȹ

INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","1. ���ü����� ����� ����","1. ���ü����� ����","1. ���ü����� ���ǿ� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","1. ���ü����� ����� ����","1. ���ü����� ����","2. ���ü����� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","1. ���ü����� ����� ����","2. ���ü����� ������ ����","1. ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","1. ���ü����� ����� ����","2. ���ü����� ������ ����","2. ����");

INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","2. ������ȹ�� ����� ���","1. ������ȹ�� ����","1. ������ȹ�� ��ǥ�� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","2. ������ȹ�� ����� ���","1. ������ȹ�� ����","2. ������ȹ�� ����� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","2. ������ȹ�� ����� ���","1. ������ȹ�� ����","3. �츮���� ������ȹ�� ��õ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","2. ������ȹ�� ����� ���","2. ������ȹ�� ���","1. �ְ�ȯ���� �����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","2. ������ȹ�� ����� ���","2. ������ȹ�� ���","2. �ڿ�ȯ��");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","2. ������ȹ�� ����� ���","2. ������ȹ�� ���","3. ���°���");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","2. ������ȹ�� ����� ���","2. ������ȹ�� ���","4. �ٸ�ȯ��");

INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","3. ������ȹ�� �ι�   ��ȹ","1. ��Ȱ�ǰ�ȹ","1. �ٸ���Ȱ���� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","3. ������ȹ�� �ι�   ��ȹ","1. ��Ȱ�ǰ�ȹ","2. �ٸ��ֱ��̷�");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","3. ������ȹ�� �ι�   ��ȹ","2. �����̿��ȹ","1. ���߹е� �� �뵵���");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","3. ������ȹ�� �ι�   ��ȹ","2. �����̿��ȹ","2. ȹ�� �� ������ȹ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","3. ������ȹ�� �ι�   ��ȹ","2. �����̿��ȹ","3. ��ġ��ȹ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","3. ������ȹ�� �ι�   ��ȹ","3. ��ݽü���ȹ","1. ����ü���ȹ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","3. ������ȹ�� �ι�   ��ȹ","3. ��ݽü���ȹ","2. Ŀ�´�Ƽ�ü���ȹ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","3. ������ȹ�� �ι�   ��ȹ","3. ��ݽü���ȹ","3. ����ó���ü���ȹ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","3. ������ȹ�� �ι�   ��ȹ","4. �ܺΰ�����ȹ","1. ���� �� ������ȹ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","3. ������ȹ�� �ι�   ��ȹ","4. �ܺΰ�����ȹ","2. �����Ϳ� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","3. ������ȹ�� �ι�   ��ȹ","4. �ܺΰ�����ȹ","3. ���½����̽�");

INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","4. ����������ȹ�� ����� ����","1. ����������ȹ�� ����� ����","1. ����������ȹ�� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","4. ����������ȹ�� ����� ����","1. ����������ȹ�� ����� ����","2. ����������ȹ�� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","4. ����������ȹ�� ����� ����","2. ����������ȹ�� ����","1. ��������");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","4. ����������ȹ�� ����� ����","2. ����������ȹ�� ����","2. ��Ȳ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","4. ����������ȹ�� ����� ����","2. ����������ȹ�� ����","3. ��ǥ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","4. ����������ȹ�� ����� ����","2. ����������ȹ�� ����","4. ��ȹ�Ծ� �� �ֹ��ǰ߼���");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","4. ����������ȹ�� ����� ����","2. ����������ȹ�� ����","5. ��ȹ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","4. ����������ȹ�� ����� ����","2. ����������ȹ�� ����","6. ��ȹ���� �� �");

INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","5. �������� ��ȹ��Һ� �ۼ�����","1. ���� �� ȹ����ȹ","1. ������ȹ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","5. �������� ��ȹ��Һ� �ۼ�����","1. ���� �� ȹ����ȹ","2. ȹ����ȹ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","5. �������� ��ȹ��Һ� �ۼ�����","2. ���๰��ȹ","1. ���๰�뵵��ȹ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","5. �������� ��ȹ��Һ� �ۼ�����","2. ���๰��ȹ","2. �е���ȹ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","5. �������� ��ȹ��Һ� �ۼ�����","2. ���๰��ȹ","3. ���� �� ��ġ��ȹ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","5. �������� ��ȹ��Һ� �ۼ�����","2. ���๰��ȹ","4. ���๰ �ܰ���ȹ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","5. �������� ��ȹ��Һ� �ۼ�����","3. ������ȹ","1. ����������ȹ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","5. �������� ��ȹ��Һ� �ۼ�����","3. ������ȹ","2. �������ȹ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","5. �������� ��ȹ��Һ� �ۼ�����","3. ������ȹ","3. ���ൿ�� �� �����ŵ�����ȹ ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","5. �������� ��ȹ��Һ� �ۼ�����","4. �����ȹ","1. ����� ���� ��ȹ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","5. �������� ��ȹ��Һ� �ۼ�����","5. ȯ�������ȹ","1. ȯ�������ȹ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","5. �������� ��ȹ��Һ� �ۼ�����","6. ��Ÿ","1. Ư����ȹ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ü��� �� ������ȹ","5. �������� ��ȹ��Һ� �ۼ�����","6. ��Ÿ","2. �μ�Ƽ�� �� �г�Ƽ");


-- 3���� ���ð��߷�
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","1. ���ð����� ���ǿ� ���","1. ���ð����� ����","1. �ʿ伺�� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","1. ���ð����� ���ǿ� ���","1. ���ð����� ����","2. ������ ���");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","1. ���ð����� ���ǿ� ���","2. ���ð����� �����̷�","1. ���ð��߰� �������");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","1. ���ð����� ���ǿ� ���","2. ���ð����� �����̷�","2. ���ð����� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","1. ���ð����� ���ǿ� ���","2. ���ð����� �����̷�","3. ���ü���� ���ð���");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","1. ���ð����� ���ǿ� ���","2. ���ð����� �����̷�","4. ���ü���������");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","1. ���ð����� ���ǿ� ���","3. ���ð����� ����","1. �츮���� ���ð����� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","1. ���ð����� ���ǿ� ���","3. ���ð����� ����","2. �ֱ��� ����");

INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","2. ���ð����� ������ ����","1. ����м�","1. ���俹���� �ʿ伺");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","2. ���ð����� ������ ����","1. ����м�","2. ���俹���� ���");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","2. ���ð����� ������ ����","2. ��������","1. ���������� ���");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","2. ���ð����� ������ ����","2. ��������","2. ���������� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","2. ���ð����� ������ ����","3. ���� �� ��ȹ","1. ���߸�ǥ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","2. ���ð����� ������ ����","3. ���� �� ��ȹ","2. ����м��� Ÿ�缺 ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","2. ���ð����� ������ ����","3. ���� �� ��ȹ","3. ��ȹ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","2. ���ð����� ������ ����","4. ���� �� ����","1. ���߻���� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","2. ���ð����� ������ ����","4. ���� �� ����","2. �Ǽ��� ó��");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","2. ���ð����� ������ ����","4. ���� �� ����","3. �ü������� �ڻ����");

INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","3. ���ð����� ����","1. ���ð��߻������","1. ���������������� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","3. ���ð����� ����","1. ���ð��߻������","2. ���ð��߹��� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","3. ���ð����� ����","1. ���ð��߻������","3. ���� �� �ְ�ȯ��������� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","3. ���ð����� ����","1. ���ð��߻������","4. ���������������� ����Ư������ ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","3. ���ð����� ����","1. ���ð��߻������","5. ���ù��� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","3. ���ð����� ����","1. ���ð��߻������","6. ������� �� ���߿����� ������ ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","3. ���ð����� ����","1. ���ð��߻������","7. ����������� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","3. ���ð����� ����","2. ��ݽü��� ���� ����","1. ���ð�ȹ�ü��� ����,���� �� ��ġ���ؿ� ���� ��Ģ�� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","3. ���ð����� ����","2. ��ݽü��� ���� ����","2. ��������� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","3. ���ð����� ����","2. ��ݽü��� ���� ����","3. ���ð��� �� ���� � ���� ������ ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","3. ���ð����� ����","2. ��ݽü��� ���� ����","4. ü���ü��� ��ġ �� �̿뿡 ���� ������ ����");

INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","4. ���ð����� ����","1. ������ü�� ���� �з�","1. ��������");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","4. ���ð����� ����","1. ������ü�� ���� �з�","2. �ΰ�����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","4. ���ð����� ����","2. ���ߴ������ ���� �з�","1. �Ű���");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","4. ���ð����� ����","2. ���ߴ������ ���� �з�","2. �簳��(����������)");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","4. ���ð����� ����","3. ���Ա�ɿ� ���� �з�","1. ���Ͽ뵵����(�ְŵ���, �������, �����޾絵�� ��)");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","4. ���ð����� ����","3. ���Ա�ɿ� ���� �з�","2. ���յ���");

INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","5. ���ð����� ����","1. ���ð��߱��","1. ���߱Ǿ絵��(TDR)");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","5. ���ð����� ����","1. ���ð��߱��","2. ���߱����߽ɰ���(TOD)");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","5. ���ð����� ����","1. ���ð��߱��","3. ��ȹ��������(PUD)");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","5. ���ð����� ����","1. ���ð��߱��","4. ���谳�߼���");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","5. ���ð����� ����","2. Ÿ�缺�м�","1. �繫�� Ÿ�缺");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","5. ���ð����� ����","2. Ÿ�缺�м�","2. ������ Ÿ�缺");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","5. ���ð����� ����","2. Ÿ�缺�м�","3. �ı�ȿ���м�");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","5. ���ð����� ����","3. ���ø�����","1. �ε��긶����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","5. ���ð����� ����","3. ���ø�����","2. ���ø�����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","5. ���ð����� ����","3. ���ø�����","3. �ŵ��ø�����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","5. ���ð����� ����","4. ������޹��","1. �������޹��");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","5. ���ð����� ����","4. ������޹��","2. ��ä���޹��");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","5. ���ð����� ����","4. ������޹��","3. ���������� ������޹��(BTL, BTO, BOT ��)");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","5. ���ð����� ����","5. �ε������","1. �ε�������� ����� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","5. ���ð����� ����","5. �ε������","2. �ΰ��� �ε��갳�߱���");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���ð��߷�","5. ���ð����� ����","5. �ε������","3. �ΰ��յ��� �ε��갳�߱���");



-- 4���� 
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","1. ���� �� ������ȹ�� ����","1. ���� �� ������ȹ�� ���� �� �ʿ伺","1. ���� �� ������ȹ�� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","1. ���� �� ������ȹ�� ����","1. ���� �� ������ȹ�� ���� �� �ʿ伺","2. ���� �� ������ȹ�� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","1. ���� �� ������ȹ�� ����","1. ���� �� ������ȹ�� ���� �� �ʿ伺","3. ���� �� ������ȹ�� Ÿ ��ȹ���� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","1. ���� �� ������ȹ�� ����","1. ���� �� ������ȹ�� ���� �� �ʿ伺","4. ���� �� ������ȹ�� �ʿ伺");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","1. ���� �� ������ȹ�� ����","2. ������ ����","1. ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","1. ���� �� ������ȹ�� ����","2. ������ ����","2. ������ ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","1. ���� �� ������ȹ�� ����","2. ������ ����","3. ���� �� ������ȹ�� Ư¡�� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","1. ���� �� ������ȹ�� ����","3. ���� �� ������ȹ�� ������ ����","1. ������ ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","1. ���� �� ������ȹ�� ����","3. ���� �� ������ȹ�� ������ ����","2. ���� �� ������ȹ�� ��õ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","1. ���� �� ������ȹ�� ����","3. ���� �� ������ȹ�� ������ ����","3. ������ȹ�� �ǻ�� ������");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","1. ���� �� ������ȹ�� ����","3. ���� �� ������ȹ�� ������ ����","4. ������ȹü���� ����");

INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","2. ���� ���� ������ ��ȹ ����","1. �������� ����","1. ���� �� ������ ����� �ǹ�");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","2. ���� ���� ������ ��ȹ ����","1. �������� ����","2. ����ȹ���� ��Ģ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","2. ���� ���� ������ ��ȹ ����","1. �������� ����","3. ��ȹ�����μ��� ���� �� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","2. ���� ���� ������ ��ȹ ����","1. �������� ����","4. �ѱ��� ���� �� ������ȹü��");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","2. ���� ���� ������ ��ȹ ����","2. ��ȹ����","1. ��ȹ�� �ǹ�");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","2. ���� ���� ������ ��ȹ ����","2. ��ȹ����","2. ��ȹ������ ��ȹ�̷��� �ߴ�");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","2. ���� ���� ������ ��ȹ ����","2. ��ȹ����","3. �����̷а� �ֹ�����");

INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","3. ���� �� ������ȹ �̷�","1. ���������̷�","1. �⺻�����̷�");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","3. ���� �� ������ȹ �̷�","1. ���������̷�","2. �Ű����̷�");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","3. ���� �� ������ȹ �̷�","1. ���������̷�","3. ��������̷�");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","3. ���� �� ������ȹ �̷�","1. ���������̷�","4. ������ �����̷�");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","3. ���� �� ������ȹ �̷�","1. ���������̷�","5. �������� �����̷�");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","3. ���� �� ������ȹ �̷�","1. ���������̷�","6. ��Ÿ ���������̷�");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","3. ���� �� ������ȹ �̷�","2. ���������̷�","1. �߽����̷�");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","3. ���� �� ������ȹ �̷�","2. ���������̷�","2. ��������̷�");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","3. ���� �� ������ȹ �̷�","2. ���������̷�","3. �ְ�������");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","3. ���� �� ������ȹ �̷�","2. ���������̷�","4. ��Ÿ �������� �̷�");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","3. ���� �� ������ȹ �̷�","3. ����� �����̷�","1. ����� ���������̷��� ���");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","3. ���� �� ������ȹ �̷�","3. ����� �����̷�","2. ������ ���������̷�");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","3. ���� �� ������ȹ �̷�","3. ����� �����̷�","3. �����������̷�");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","3. ���� �� ������ȹ �̷�","3. ����� �����̷�","4. ���� ���������̷��� ����");

INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","4. ���� �� ������ȹ�� ����","1. �ڷ����� �м��� ��ȹ�� ��","1. ��������� ������ ����(�ڷ��� ��ó, �ڷ���� ���, �ڷ���� ����, ���������� Ȱ��, �������� ü��)");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","4. ���� �� ������ȹ�� ����","1. �ڷ����� �м��� ��ȹ�� ��","2. ���� �� ������ȹ�� ��(������ȹ ���� ����, ������ȹ ������ ����, ��������� ��)");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","4. ���� �� ������ȹ�� ����","2. �ι��� ��ȹ ","1. ��ȹ�α��� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","4. ���� �� ������ȹ�� ����","2. �ι��� ��ȹ ","2. �����̿��ȹ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","4. ���� �� ������ȹ�� ����","2. �ι��� ��ȹ ","3. ���������ȹ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","4. ���� �� ������ȹ�� ����","2. �ι��� ��ȹ ","4. ��������ȹ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","4. ���� �� ������ȹ�� ����","2. �ι��� ��ȹ ","5. ȯ�溸�� �� �ڿ�������ȹ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","4. ���� �� ������ȹ�� ����","2. �ι��� ��ȹ ","6. �ְ�ȯ���ȹ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","4. ���� �� ������ȹ�� ����","2. �ι��� ��ȹ ","7. ��ȸ���߰�ȹ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","4. ���� �� ������ȹ�� ����","2. �ι��� ��ȹ ","8. ���̰�ȹ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","4. ���� �� ������ȹ�� ����","2. �ι��� ��ȹ ","9. �����ȹ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","4. ���� �� ������ȹ�� ����","2. �ι��� ��ȹ ","10. �����ȹ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","4. ���� �� ������ȹ�� ����","3. ������ ����","1. �ѱ� ���� �� ������ȹ�� ������ ���� ���ǰ� ��ȭ");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","4. ���� �� ������ȹ�� ����","3. ������ ����","2. �ѱ� ���� �� ������ȹ�� ���� ����");

INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","5. �츮������ ���� �� ������ȹ","1. �������հ�ȹ","1. �������հ�ȹ�� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","5. �츮������ ���� �� ������ȹ","1. �������հ�ȹ","2. �������հ�ȹ�� �ֿ䳻��");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","5. �츮������ ���� �� ������ȹ","1. �������հ�ȹ","3. �����̿��� ������ ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","5. �츮������ ���� �� ������ȹ","1. �������հ�ȹ","4. �������հ�ȹ�� ��");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","5. �츮������ ���� �� ������ȹ","2. �����������ȹ","1. �����������ȹ�� �ʿ伺");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","5. �츮������ ���� �� ������ȹ","2. �����������ȹ","2. �����������ȹ�� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","5. �츮������ ���� �� ������ȹ","2. �����������ȹ","3. �����������ȹ�� �ֿ䳻��");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","5. �츮������ ���� �� ������ȹ","2. �����������ȹ","4. �����������ȹ�� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","5. �츮������ ���� �� ������ȹ","3. ������ȹ","1. ������ȹ�� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","5. �츮������ ���� �� ������ȹ","3. ������ȹ","2. ������ȹ�� ��������");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","5. �츮������ ���� �� ������ȹ","3. ������ȹ","3. ������ȹ�� ��");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","5. �츮������ ���� �� ������ȹ","4. �������ð�ȹ","1. �������ð�ȹ�� ����");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","5. �츮������ ���� �� ������ȹ","4. �������ð�ȹ","2. �������ð�ȹ�� ��������");
INSERT INTO classification(subject,large,medium,small) 
VALUES ("���� �� ������ȹ","5. �츮������ ���� �� ������ȹ","4. �������ð�ȹ","3. �������ð�ȹ�� ��");


-- 5���� ���ð�ȹ ���� ����




INSERT INTO problemheader(year,serial,type,subject,classify,level,large,medium,small,pNum)
		VALUES ("2006","1","A��","���ð�ȹ��","app","high","1. ������ ���� �� ���ùߴ�","1. ���ÿ� ���ù���","1. ������ ����� ����",3);
INSERT INTO problemheader(year,serial,type,subject,classify,level,large,medium,small,pNum)
		VALUES ("2006","1","A��","���ð�ȹ��","basic","normal","1. ������ ���� �� ���ùߴ�","1. ���ÿ� ���ù���","1. ������ ����� ����",0);





INSERT INTO problembody(problem,addition,choice1,choice2,choice3,choice4,keyword,solution,answer)
		VALUES("��������","�߰�����(�׸�)","����1","����2","����3","����4","K","S",1);
INSERT INTO problembody(problem,addition,choice1,choice2,choice3,choice4,keyword,solution,answer)
		VALUES("��������2","�߰�����(�׸�)","����1","����2","����3","����4","K","S",3);






show tables;
SELECT * FROM basicoption1;
SELECT * FROM basicoption2;
SELECT * FROM classification;
SELECT * FROM problemheader;
SELECT * FROM problembody;