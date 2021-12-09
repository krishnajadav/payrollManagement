package com.asdc.payroll_management.appraisal.final_rating;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.asdc.payroll_management.DataBaseCache.Appraisal;
import com.asdc.payroll_management.DataBaseCache.AppraisalCache;
import com.asdc.payroll_management.DataBaseCache.Employee;
import com.asdc.payroll_management.DataBaseCache.EmployeeCache;

@TestInstance(Lifecycle.PER_CLASS)
class EmployeeAppraisalRatingConcreteFactoryTest {

	EmployeeAppraisalRatingConcreteFactory underTest = new EmployeeAppraisalRatingConcreteFactory();

	@Test
	void classExistanceTest() {
		try {
			Class<?> classFinder = Class
					.forName("com.asdc.payroll_management.appraisal.final_rating.FinalRatingDAOImpl");
			assertNotNull(classFinder);
		} catch (ClassNotFoundException e) {
			fail("class not found");
		}
	}

	@Test
	void getFinalRatingDataTest() {
		try {
			Appraisal appraisal = new Appraisal("EMP_ID", "MANAGER_ID", "4.8",
					"Good team performance throughout the assigned project. Maintained a good learning curve and paid attention to self development throughout the course of the project. Handled team conflicts very well and was always up for Knowledge transfer sessions to the new resources recruited to the project.",
					"4.9",
					"Good team performance throughout the assigned project. Maintained a good learning curve and paid attention to self development throughout the course of the project. Handled team conflicts very well and was always up for Knowledge transfer sessions to the new resources recruited to the project.",
					"[Orbis, Micro variables, Compliance, MDB]", "[SE 1, AWS, JS, Swings]", null, "5",
					"[projectName : Orbis || projectSize : Small scale || employeeContribution : 45 || error : null, projectName : Micro variables || projectSize : Medium scale || employeeContribution : 7 || error : null, projectName : Compliance || projectSize : Medium scale || employeeContribution : 12 || error : null, projectName : MDB || projectSize : Large scale || employeeContribution : 20 || error : null]");

			Employee employee1 = new Employee("EMP_ID", "EMP_NAME", null, null, null, null, null, "1", "DEPT_ID_1",
					null, null);

			AppraisalCache appraisalCache = Mockito.mock(AppraisalCache.class);
			MockedStatic<AppraisalCache> mocked = mockStatic(AppraisalCache.class);
			mocked.when(AppraisalCache::getInstance).thenReturn(appraisalCache);
			Mockito.when(appraisalCache.getIndividualAppraisals("EMP_ID")).thenReturn(null);

			EmployeeCache employeeCache = Mockito.mock(EmployeeCache.class);
			MockedStatic<EmployeeCache> mockedEmployeeCache = mockStatic(EmployeeCache.class);
			mockedEmployeeCache.when(EmployeeCache::getInstance).thenReturn(employeeCache);
			Mockito.when(employeeCache.getEmployee("MANAGER_ID")).thenReturn(employee1);
			Mockito.when(employeeCache.getEmployee("EMP_ID")).thenReturn(employee1);

			Double actualResult = underTest.getFinalRating("EMP_ID");
			assertNull(actualResult);

			Mockito.when(appraisalCache.getIndividualAppraisals("EMP_ID")).thenReturn(appraisal);
			actualResult = underTest.getFinalRating("EMP_ID");
			assertEquals(3.7d, actualResult);

			appraisal.setProjectsRating(
					"[projectName : Orbis || projectSize : Small scale || employeeContribution : 45 || error : null]");
			actualResult = underTest.getFinalRating("EMP_ID");
			assertEquals(2.62d, actualResult);

			appraisal.setManagerRating("2");
			appraisal.setProjectsRating(
					"[projectName : Orbis || projectSize : Small scale || employeeContribution : 10 || error : null]");
			actualResult = underTest.getFinalRating("EMP_ID");
			assertEquals(1.68d, actualResult);

			appraisal.setManagerRating("4.9");
			appraisal.setProjectsRating(
					"[projectName : Orbis || projectSize : Large scale || employeeContribution : 100 || error : null]");
			actualResult = underTest.getFinalRating("EMP_ID");
			assertEquals(4.93d, actualResult);

			appraisal.setProjectsRating(
					"[projectName : Orbis || projectSize : Large scale || employeeContribution : 35 || error : null]");
			actualResult = underTest.getFinalRating("EMP_ID");
			assertEquals(3.9d, actualResult);

			appraisal.setProjectsRating(
					"[projectName : Orbis || projectSize : Large scale || employeeContribution : 85 || error : null]");
			actualResult = underTest.getFinalRating("EMP_ID");
			assertEquals(4.75d, actualResult);

			appraisal.setTechnologiesLearnt("[SE 1, AWS, JS, Swings, VueJs, REST, HTML, CSS]");
			actualResult = underTest.getFinalRating("EMP_ID");
			assertEquals(4.78d, actualResult);

			appraisal.setTechnologiesLearnt("[SE 1]");
			actualResult = underTest.getFinalRating("EMP_ID");
			assertEquals(4.68d, actualResult);

			appraisal.setTechnologiesLearnt("[SE 1, AWS]");
			actualResult = underTest.getFinalRating("EMP_ID");
			assertEquals(4.68d, actualResult);

			appraisal.setTechnologiesLearnt("");
			actualResult = underTest.getFinalRating("EMP_ID");
			assertEquals(4.63d, actualResult);

			mocked.close();
			mockedEmployeeCache.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
