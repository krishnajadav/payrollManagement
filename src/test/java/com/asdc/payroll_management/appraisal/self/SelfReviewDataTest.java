package com.asdc.payroll_management.appraisal.self;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class SelfReviewDataTest {

	@Test
	void classExistanceTest() {
		try {
			Class<?> classFinder = Class.forName("com.asdc.payroll_management.appraisal.self.SelfReviewData");
			assertNotNull(classFinder);
		} catch (ClassNotFoundException e) {
			fail("class not found");
		}
	}

	@Test
	void selfReviewDataTest() {
		try {
			SelfReviewData selfReviewData = new SelfReviewData();
			ArrayList<String> projects = new ArrayList<String>();
			projects.add("Project 1");
			projects.add("Project 2");
			projects.add("Project 3");

			ArrayList<String> techsLeaned = new ArrayList<String>();
			techsLeaned.add("Tech 1");
			techsLeaned.add("Tech 2");
			techsLeaned.add("Tech 3");

			selfReviewData.setComments(
					"Good team performance throughout the assigned project. Maintained a good learning curve and paid attention to self development throughout the course of the project. Handled team conflicts very well and was always up for Knowledge transfer sessions to the new resources recruited to the project.");
			selfReviewData.setEmployeeID("26119");
			selfReviewData.setEmployeeName("Chandan Jha");
			selfReviewData.setProjectsParticipated(projects);
			selfReviewData.setRating("4.6");
			selfReviewData.setTechnologiesLearnt(techsLeaned);

			assertEquals(
					"Good team performance throughout the assigned project. Maintained a good learning curve and paid attention to self development throughout the course of the project. Handled team conflicts very well and was always up for Knowledge transfer sessions to the new resources recruited to the project.",
					selfReviewData.getComments());
			assertEquals("26119", selfReviewData.getEmployeeID());
			assertEquals("Chandan Jha", selfReviewData.getEmployeeName());
			assertEquals("4.6", selfReviewData.getRating());
			assertEquals(projects, selfReviewData.getProjectsParticipated());
			assertEquals(techsLeaned, selfReviewData.getTechnologiesLearnt());
		} catch (Exception e) {
			fail("Exception occured");
			e.printStackTrace();
		}
	}

	@Test
	void validateTest() {
		try {
			SelfReviewData selfReviewData = new SelfReviewData();
			ArrayList<String> projects = new ArrayList<String>();
			projects.add("Project 1");
			projects.add("Project 2");
			projects.add("Project 3");

			ArrayList<String> techsLeaned = new ArrayList<String>();
			techsLeaned.add("Tech 1");
			techsLeaned.add("Tech 2");
			techsLeaned.add("Tech 3");

			selfReviewData.setComments(
					"Good team performance throughout the assigned project. Maintained a good learning curve and paid attention to self development throughout the course of the project. Handled team conflicts very well and was always up for Knowledge transfer sessions to the new resources recruited to the project.");
			selfReviewData.setEmployeeID("26119");
			selfReviewData.setEmployeeName("Chandan Jha");
			selfReviewData.setProjectsParticipated(projects);
			selfReviewData.setRating("4.6");
			selfReviewData.setTechnologiesLearnt(techsLeaned);
			selfReviewData.validate();

			assertNull(selfReviewData.getError());

			selfReviewData.setComments("");
			selfReviewData.validate();
			assertEquals("Self Review Comments should be atleast 100 characters.<br>", selfReviewData.getError());

			selfReviewData.setComments(
					"Good team performance throughout the assigned project. Maintained a good learning curve and paid attention to self development throughout the course of the project. Handled team conflicts very well and was always up for Knowledge transfer sessions to the new resources recruited to the project.");
			selfReviewData.setEmployeeName("");
			selfReviewData.validate();
			assertEquals("Employee Name should only have alphabets and it cannot be empty.<br>",
					selfReviewData.getError());

			selfReviewData.setEmployeeName("Chandan Jha");
			selfReviewData.setRating("abc");
			selfReviewData.validate();
			assertEquals("Self Rating should be a valid number that is greater than 0 and less than 5.<br>",
					selfReviewData.getError());

			selfReviewData.setRating("4.5");
			selfReviewData.setProjectsParticipated(null);
			selfReviewData.validate();
			assertEquals("Atleast one project you participated in should be added.<br>", selfReviewData.getError());

		} catch (Exception e) {
			fail("Exception occured");
			e.printStackTrace();
		}
	}

}
