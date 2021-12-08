package com.asdc.payroll_management.appraisal.self;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.asdc.payroll_management.DataBaseCache.AppraisalCache;
import com.asdc.payroll_management.DataBaseCache.Employee;
import com.asdc.payroll_management.DataBaseCache.EmployeeCache;

class AppraisalControllerTest {

	AppraisalController underTest = new AppraisalController();

	@Test
	void classExistanceTest() {
		try {
			Class<?> classFinder = Class.forName("com.asdc.payroll_management.appraisal.self.AppraisalController");
			assertNotNull(classFinder);
		} catch (ClassNotFoundException e) {
			fail("class not found");
		}
	}

//	@Test
//	void getAppraisalModelTest() {
//		try {
//			AppraisalController appraisalController = new AppraisalController();
//			assertEquals("SelfReview", appraisalController.getAppraisalModel().getViewName());
//		} catch (Exception e) {
//			fail("Exception occured");
//			e.printStackTrace();
//		}
//	}

	@Test
	void getPeerInfoTest() {
		try {
			List<String> peers = new ArrayList<String>();
			peers.add("7, EMP_NAME_2");
			peers.add("1, EMP_NAME_3");

			HashMap<String, Employee> employeeMap = new HashMap<String, Employee>();
			Employee employee1 = new Employee("26119", "EMP_NAME_0", null, null, null, null, null, null, "DEPT_ID_1",
					null, null);
			Employee employee2 = new Employee("4", "EMP_NAME_1", null, null, null, null, null, null, "DEPT_ID_2", null,
					null);
			Employee employee3 = new Employee("7", "EMP_NAME_2", null, null, null, null, null, null, "DEPT_ID_1", null,
					null);
			Employee employee4 = new Employee("1", "EMP_NAME_3", null, null, null, null, null, null, "DEPT_ID_1", null,
					null);
			Employee employee5 = new Employee("3", "EMP_NAME_4", null, null, null, null, null, null, "DEPT_ID_2", null,
					null);
			Employee employee6 = new Employee("8", "EMP_NAME_5", null, null, null, null, null, null, "DEPT_ID_3", null,
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

			List<String> actualOutput = underTest.getPeerInfo("26119");
			if (!actualOutput.containsAll(peers) && peers.containsAll(actualOutput)) {
				fail("unexpected results while fetching peers");
			}
			mocked.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void getAppraisalDataTest() {
		try {
			AppraisalDataDAO appraisalDataDAO = new AppraisalDataDAO();
			SelfReviewData selfReviewData = new SelfReviewData();

			ArrayList<String> projects = new ArrayList<String>();
			projects.add("Project 1");
			projects.add("Project 2");
			projects.add("Project 3");

			ArrayList<String> techsLeaned = new ArrayList<String>();
			techsLeaned.add("Tech 1");
			techsLeaned.add("Tech 2");
			techsLeaned.add("Tech 3");

			AppraisalCache appraisalCache = Mockito.mock(AppraisalCache.class);
			MockedStatic<AppraisalCache> mocked = mockStatic(AppraisalCache.class);
			mocked.when(AppraisalCache::getInstance).thenReturn(appraisalCache);
			Mockito.when(appraisalCache.insert(Mockito.any())).thenReturn(true);

			selfReviewData.setComments(
					"Good team performance throughout the assigned project. Maintained a good learning curve and paid attention to self development throughout the course of the project. Handled team conflicts very well and was always up for Knowledge transfer sessions to the new resources recruited to the project.");
			selfReviewData.setEmployeeID("26119");
			selfReviewData.setEmployeeName("Chandan Jha");
			selfReviewData.setProjectsParticipated(projects);
			selfReviewData.setRating("4.6");
			selfReviewData.setTechnologiesLearnt(techsLeaned);
			appraisalDataDAO.processInput(selfReviewData);
			assertNull(selfReviewData.getError());

			selfReviewData.setComments("");
			underTest.getSelfReviewData(selfReviewData);
			assertEquals("Self Review Comments should be atleast 100 characters.<br>", selfReviewData.getError());
			mocked.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
