package com.asdc.payroll_management.appraisal.manager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.asdc.payroll_management.DataBaseCache.Appraisal;
import com.asdc.payroll_management.DataBaseCache.AppraisalCache;
import com.asdc.payroll_management.DataBaseCache.Employee;
import com.asdc.payroll_management.DataBaseCache.EmployeeCache;
import com.asdc.payroll_management.appraisal.final_rating.FinalRatingConstants;
import com.asdc.payroll_management.appraisal.self.SelfReviewData;

@TestInstance(Lifecycle.PER_CLASS)
class ManagerReviewDataDAOTest {

	ManagerReviewDataDAO underTest = new ManagerReviewDataDAO();

	@Test
	void classExistanceTest() {
		try {
			Class<?> classFinder = Class.forName("com.asdc.payroll_management.appraisal.manager.ManagerReviewDataDAO");
			assertNotNull(classFinder);
		} catch (ClassNotFoundException e) {
			fail("class not found");
		}
	}

	@Test
	void getEmployeeNamesWithIDTest() {
		try {
			List<String> employeeNamesWithID = new ArrayList<String>();
			employeeNamesWithID.add("EMP_NAME_3 (1)");
			employeeNamesWithID.add("EMP_NAME_1 (4)");

			HashMap<String, Employee> employeeMap = new HashMap<String, Employee>();
			Employee employee1 = new Employee("26119", "EMP_NAME_0", null, null, null, null, null, "1", "DEPT_ID_1",
					null, null);
			Employee employee2 = new Employee("4", "EMP_NAME_1", null, null, null, null, null, "26119", "DEPT_ID_2",
					null, null);
			Employee employee3 = new Employee("7", "EMP_NAME_2", null, null, null, null, null, "2", "DEPT_ID_1", null,
					null);
			Employee employee4 = new Employee("1", "EMP_NAME_3", null, null, null, null, null, "26119", "DEPT_ID_1",
					null, null);
			Employee employee5 = new Employee("3", "EMP_NAME_4", null, null, null, null, null, "5", "DEPT_ID_2", null,
					null);
			Employee employee6 = new Employee("8", "EMP_NAME_5", null, null, null, null, null, "3", "DEPT_ID_3", null,
					null);

			employeeMap.put(employee1.getEmployeeID(), employee1);
			employeeMap.put(employee2.getEmployeeID(), employee2);
			employeeMap.put(employee3.getEmployeeID(), employee3);
			employeeMap.put(employee4.getEmployeeID(), employee4);
			employeeMap.put(employee5.getEmployeeID(), employee5);
			employeeMap.put(employee6.getEmployeeID(), employee6);

			EmployeeCache employeeCache = Mockito.mock(EmployeeCache.class);
			MockedStatic<EmployeeCache> mocked = mockStatic(EmployeeCache.class);
			mocked.when(EmployeeCache::getInstance).thenReturn(employeeCache);
			Mockito.when(employeeCache.getAllEmployees()).thenReturn(employeeMap);
			Mockito.when(employeeCache.getEmployee("26119")).thenReturn(employee1);
			List<String> actualOutput = underTest.getEmployeeNamesWithID("26119");
			if (!(actualOutput.containsAll(employeeNamesWithID) && employeeNamesWithID.containsAll(actualOutput))) {
				fail("unexpected results while fetching peers");
			}
			mocked.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void getSelfReviewInfoTest() {
		try {
			Appraisal appraisal = new Appraisal("26119", null, "3.9", "", null, null,
					"[project1, project2, project3, project4, project5]", "[tech1, tech2, tech3]", null, null, null);
			Employee employee1 = new Employee("26119", "EMP_NAME_0", null, null, null, null, null, "1", "DEPT_ID_1",
					null, null);

			AppraisalCache appraisalCache = Mockito.mock(AppraisalCache.class);
			MockedStatic<AppraisalCache> mocked = mockStatic(AppraisalCache.class);
			mocked.when(AppraisalCache::getInstance).thenReturn(appraisalCache);
			Mockito.when(appraisalCache.getIndividualAppraisals("26119")).thenReturn(appraisal);

			EmployeeCache employeeCache = Mockito.mock(EmployeeCache.class);
			MockedStatic<EmployeeCache> mockedEmployeeCache = mockStatic(EmployeeCache.class);
			mockedEmployeeCache.when(EmployeeCache::getInstance).thenReturn(employeeCache);
			Mockito.when(employeeCache.getEmployee("26119")).thenReturn(employee1);

			List<Object> actualOutput = underTest.getSelfReviewInfo("26119");

			assertNull(actualOutput.get(1));
			assertEquals("26119", ((SelfReviewData) actualOutput.get(0)).getEmployeeID());
			assertEquals("3.9", ((SelfReviewData) actualOutput.get(0)).getRating());
			assertEquals("[project1, project2, project3, project4, project5]",
					((SelfReviewData) actualOutput.get(0)).getProjectsParticipated().toString());
			assertEquals("[tech1, tech2, tech3]",
					((SelfReviewData) actualOutput.get(0)).getTechnologiesLearnt().toString());
			mocked.close();
			mockedEmployeeCache.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void processInputTest() {
		try {
			List<ProjectParticipationData> projectParticipationDataList = new ArrayList<ProjectParticipationData>();
			ProjectParticipationData project1 = new ProjectParticipationData();
			ProjectParticipationData project2 = new ProjectParticipationData();
			ProjectParticipationData project3 = new ProjectParticipationData();

			Employee employee1 = new Employee("26119", "EMP_NAME_0", null, null, null, null, null, "1", "DEPT_ID_1",
					null, null);

			project1.setEmployeeContribution("20");
			project1.setProjectName("project1");
			project1.setProjectSize(FinalRatingConstants.ProjectSize.SMALL_SCALE.getValue());

			project2.setEmployeeContribution("20");
			project2.setProjectName("project2");
			project2.setProjectSize(FinalRatingConstants.ProjectSize.MEDIUM_SCALE.getValue());

			project3.setEmployeeContribution("20");
			project3.setProjectName("project3");
			project3.setProjectSize(FinalRatingConstants.ProjectSize.LARGE_SCALE.getValue());

			projectParticipationDataList.add(project1);
			projectParticipationDataList.add(project2);
			projectParticipationDataList.add(project3);

			ManagerReviewData managerReviewData = new ManagerReviewData();
			managerReviewData.setComments(
					"Good team performance throughout the assigned project. Maintained a good learning curve and paid attention to self development throughout the course of the project. Handled team conflicts very well and was always up for Knowledge transfer sessions to the new resources recruited to the project.");
			managerReviewData.setCommunicationSkillsRating("4.1");
			managerReviewData.setEmployeeID("26119");
			managerReviewData.setEmployeeName("Jaswanth");
			managerReviewData.setProjectsParticipated(projectParticipationDataList);
			managerReviewData.setRating("4.1");

			AppraisalCache appraisalCache = Mockito.mock(AppraisalCache.class);
			MockedStatic<AppraisalCache> mocked = mockStatic(AppraisalCache.class);
			mocked.when(AppraisalCache::getInstance).thenReturn(appraisalCache);
			Mockito.when(appraisalCache.updateManagerReview(Mockito.any())).thenReturn(true);

			EmployeeCache employeeCache = Mockito.mock(EmployeeCache.class);
			MockedStatic<EmployeeCache> mockedEmployeeCache = mockStatic(EmployeeCache.class);
			mockedEmployeeCache.when(EmployeeCache::getInstance).thenReturn(employeeCache);
			Mockito.when(employeeCache.getEmployee("26119")).thenReturn(employee1);

			List<Object> actualResult = underTest.processInput(managerReviewData);
			assertNull(actualResult.get(1));
			assertNull(((ManagerReviewData) actualResult.get(0)).getError());
			mocked.close();
			mockedEmployeeCache.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
