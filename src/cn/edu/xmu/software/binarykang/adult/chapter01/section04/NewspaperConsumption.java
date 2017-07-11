package cn.edu.xmu.software.binarykang.adult.chapter01.section04;

import java.util.List;

import cn.edu.xmu.software.binarykang.adult.AdultBaseAction;
import cn.edu.xmu.software.binarykang.common.formatter.GF;
import cn.edu.xmu.software.binarykang.common.rowtype.BaseRow;
import cn.edu.xmu.software.binarykang.common.rowtype.ListFactory;
import cn.edu.xmu.software.binarykang.common.rowtype.MultiType;
import cn.edu.xmu.software.binarykang.common.rowtype.SingleValue;
import cn.edu.xmu.software.binarykang.word.Docx;
import cn.edu.xmu.software.binarykang.xlsx.Xlsx;

/**
 * ����1.4.2��ֽ�������
 * 
 * @author KangBin <bingyingsuolian@163.com>
 * @since 2014-08-14
 *
 */
public final class NewspaperConsumption extends AdultBaseAction
{
	private double averageNewspaperConsumeMoney;

	private List<BaseRow> genderData;
	private List<BaseRow> urbanData;
	private List<BaseRow> areaData;
	private List<BaseRow> occupationData;
	private List<BaseRow> educationData;
	private List<BaseRow> ageData;
	private List<List<BaseRow>> data;

	public NewspaperConsumption(Docx docx, Xlsx xlsx)
	{
		super(docx, xlsx);
		genderData = ListFactory.getBaseRows();
		urbanData = ListFactory.getBaseRows();
		ageData = ListFactory.getBaseRows();
		educationData = ListFactory.getBaseRows();
		occupationData = ListFactory.getBaseRows();
		areaData = ListFactory.getBaseRows();		
		data = ListFactory.getVarTypeRows();
		data.add(genderData);
		data.add(urbanData);
		data.add(ageData);
		data.add(educationData);
		data.add(occupationData);
		data.add(areaData);
	}

	@Override
	protected void readData()
	{
		// ��ȡ1.4.1ͼ�����������һ�������е��˾������������
		averageNewspaperConsumeMoney = SingleValue.read(xlsx,
				"1.4.2��ֽ���������һ�������еı�ֽ������", 1, 1);

		MultiType.read(xlsx, data, "��ͬ�˿��������Էѱ�ֽ�������");
	}

	@Override
	protected void replace()
	{
		// �滻1.4.2��ֽ���������һ�������еı�ֽ������
		tr("${average_newspaper_consume_money}",
				tf(averageNewspaperConsumeMoney));
		// replace gender
		tr("${male_newspaper_consume_money}", tf(genderData.get(0).value));
		tr("${female_newspaper_consume_money}", tf(genderData.get(1).value));
		tr("${gender_newspaper_consume_money_diff}", genderData.get(0).value>genderData.get(1).value?"�߳�":"�ͳ�");
		tr("${gender_newspaper_consume_money_diff_num}", tf(Math.abs(genderData.get(0).value-genderData.get(1).value)));
		
		// �滻������ص�����
		double urbanConsumeMoney = urbanData.get(0).value;
		double villageConsumeMoney = urbanData.get(1).value;
		tr("${urban_newspaper_consume_money}", tf(urbanConsumeMoney));
		tr("${village_newspaper_consume_money}", tf(villageConsumeMoney));
		tr("${urban_minus_village_consume_money}", urbanConsumeMoney > villageConsumeMoney ? "�߳�":"�ͳ�");
		tr("${urban_minus_village_consume_money_num}", tf(Math.abs(urbanConsumeMoney-villageConsumeMoney)));

		// �滻������ص�����
		BaseRow.sort(ageData);
		tr("${best_age_newspaper_consume}", ageData.get(0).key);
		tr("${best_age_newspaper_consume_money}", tf(ageData.get(0).value));
		tr("${worst_second_age_newspaper_consume}", ageData.get(ageData.size()-2).key);
		tr("${worst_age_newspaper_consume}", ageData.get(ageData.size()-1).key);
		tr("${worst_second_age_newspaper_consume_money}", tf(ageData.get(ageData.size()-2).value));
		tr("${worst_age_newspaper_consume_money}", tf(ageData.get(ageData.size()-1).value));
		
		// �滻�ܽ����̶���ص�����
		BaseRow.sort(educationData);
		tr("${best_education_newspaper_consume}", educationData.get(0).key);
		tr("${worst_education_newspaper_consume}", educationData.get(educationData.size()-1).key);
		tr("${best_education_newspaper_consume_money}", tf(educationData.get(0).value));
		tr("${worst_education_newspaper_consume_money}", tf(educationData.get(educationData.size()-1).value));
		
		// �滻ְҵ��ص�����
		BaseRow.sortExceptLast(occupationData);
		tr("${best_occupation_newspaper_consume}", occupationData.get(0).key);
		tr("${best_occupation_newspaper_consume_money}", tf(occupationData.get(0).value));
		tr("${second_occupation_newspaper_consume}", occupationData.get(1).key);
		tr("${third_occupation_newspaper_consume}", occupationData.get(2).key);
		tr("${fourth_occupation_newspaper_consume}", occupationData.get(3).key);
		tr("${second_occupation_newspaper_consume_money}", tf(occupationData.get(1).value));
		tr("${third_occupation_newspaper_consume_money}", tf(occupationData.get(2).value));
		tr("${fourth_occupation_newspaper_consume_money}", tf(occupationData.get(3).value));
		tr("${fifth_occupation_newspaper_consume}", occupationData.get(4).key);
		tr("${sixth_occupation_newspaper_consume}", occupationData.get(5).key);
		tr("${seventh_occupation_newspaper_consume}", occupationData.get(6).key);
		tr("${eighth_occupation_newspaper_consume}", occupationData.get(7).key);
		tr("${fifth_occupation_newspaper_consume_money}", tf(occupationData.get(4).value));
		tr("${sixth_occupation_newspaper_consume_money}", tf(occupationData.get(5).value));
		tr("${seventh_occupation_newspaper_consume_money}", tf(occupationData.get(6).value));
		tr("${eighth_occupation_newspaper_consume_money}", tf(occupationData.get(7).value));
		//tr("${second_last_occupation_newspaper_consume}", occupationData.get(occupationData.size() - 2).key);
		//tr("${second_last_occupation_newspaper_consume_money}", tf(occupationData.get(occupationData.size() - 2).value));
		tr("${worst_occupation_newspaper_consume}", occupationData.get(occupationData.size() - 2).key);
		tr("${worst_occupation_newspaper_consume_money}", tf(occupationData.get(occupationData.size() - 2).value));
		
		// �滻������ص�����
		BaseRow.sort(areaData);
		tr("${best_area_newspaper_consume}", areaData.get(0).key);
		tr("${best_area_newspaper_consume_money}", tf(areaData.get(0).value));
		tr("${second_area_newspaper_consume}", areaData.get(1).key);
		tr("${second_area_newspaper_consume_money}", tf(areaData.get(1).value));
		tr("${worst_area_newspaper_consume}", areaData.get(areaData.size()-1).key);
		tr("${worst_area_newspaper_consume_money}", tf(areaData.get(areaData.size()-1).value));
	}

	@Override
	protected void chart()
	{
		BaseRow.sort(areaData);
		MultiType.table(docx, data, "${newspaper_consumption_key_%d}",
				"${newspaper_consumption_value_%d}",
//				"Resource/adult/chapter01/consumption_row.xml", 
				"Resource/adult/chapter01/media_contact_type_area_row.xml",
				GF.t);
	}
}