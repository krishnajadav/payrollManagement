package com.asdc.payroll_management.appraisal.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.asdc.payroll_management.appraisal.final_rating.FinalRatingConstants;

@TestInstance(Lifecycle.PER_CLASS)
class ManagerReviewDataTest {

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
	void managerReviewDataTest() {
		try {
			ManagerReviewData managerReviewData = new ManagerReviewData();

			ProjectParticipationData project1 = new ProjectParticipationData();
			ProjectParticipationData project2 = new ProjectParticipationData();
			ProjectParticipationData project3 = new ProjectParticipationData();

			project1.setEmployeeContribution("20");
			project1.setProjectName("project1");
			project1.setProjectSize(FinalRatingConstants.ProjectSize.SMALL_SCALE.getValue());

			project2.setEmployeeContribution("20");
			project2.setProjectName("project2");
			project2.setProjectSize(FinalRatingConstants.ProjectSize.MEDIUM_SCALE.getValue());

			project3.setEmployeeContribution("20");
			project3.setProjectName("project3");
			project3.setProjectSize(FinalRatingConstants.ProjectSize.LARGE_SCALE.getValue());

			List<ProjectParticipationData> projectParticipationDataList = new ArrayList<ProjectParticipationData>();
			projectParticipationDataList.add(project1);
			projectParticipationDataList.add(project2);
			projectParticipationDataList.add(project3);

			managerReviewData.setComments(
					"Good team performance throughout the assigned project. Maintained a good learning curve and paid attention to self development throughout the course of the project. Handled team conflicts very well and was always up for Knowledge transfer sessions to the new resources recruited to the project.");
			managerReviewData.setEmployeeID("26119");
			managerReviewData.setEmployeeName("Chandan Jha");
			managerReviewData.setProjectsParticipated(projectParticipationDataList);
			managerReviewData.setCommunicationSkillsRating("4.9");
			managerReviewData.setRating("4.6");

			assertEquals(
					"Good team performance throughout the assigned project. Maintained a good learning curve and paid attention to self development throughout the course of the project. Handled team conflicts very well and was always up for Knowledge transfer sessions to the new resources recruited to the project.",
					managerReviewData.getComments());
			assertEquals("26119", managerReviewData.getEmployeeID());
			assertEquals("Chandan Jha", managerReviewData.getEmployeeName());
			assertEquals("4.6", managerReviewData.getRating());
			assertEquals("4.9", managerReviewData.getCommunicationSkillsRating());
			assertEquals(projectParticipationDataList, managerReviewData.getProjectsParticipated());
		} catch (Exception e) {
			fail("Exception occured");
			e.printStackTrace();
		}
	}

	@Test
	void validateTest() {
		try {
			ManagerReviewData managerReviewData = new ManagerReviewData();

			ProjectParticipationData project1 = new ProjectParticipationData();
			ProjectParticipationData project2 = new ProjectParticipationData();
			ProjectParticipationData project3 = new ProjectParticipationData();

			project1.setEmployeeContribution("20");
			project1.setProjectName("project1");
			project1.setProjectSize(FinalRatingConstants.ProjectSize.SMALL_SCALE.getValue());

			project2.setEmployeeContribution("20");
			project2.setProjectName("project2");
			project2.setProjectSize(FinalRatingConstants.ProjectSize.MEDIUM_SCALE.getValue());

			project3.setEmployeeContribution("20");
			project3.setProjectName("project3");
			project3.setProjectSize(FinalRatingConstants.ProjectSize.LARGE_SCALE.getValue());

			List<ProjectParticipationData> projectParticipationDataList = new ArrayList<ProjectParticipationData>();
			projectParticipationDataList.add(project1);
			projectParticipationDataList.add(project2);
			projectParticipationDataList.add(project3);

			managerReviewData.setComments(
					"Good team performance throughout the assigned project. Maintained a good learning curve and paid attention to self development throughout the course of the project. Handled team conflicts very well and was always up for Knowledge transfer sessions to the new resources recruited to the project.");
			managerReviewData.setEmployeeID("26119");
			managerReviewData.setEmployeeName("Chandan Jha");
			managerReviewData.setProjectsParticipated(projectParticipationDataList);
			managerReviewData.setRating("4.6");
			managerReviewData.setCommunicationSkillsRating("4.9");
			managerReviewData.validate();

			assertNull(managerReviewData.getError());

			managerReviewData.setComments("");
			managerReviewData.validate();
			assertEquals("Manager Review Comments should be atleast 100 characters.<br>", managerReviewData.getError());

			managerReviewData.setComments(
					"Good team performance throughout the assigned project. Maintained a good learning curve and paid attention to self development throughout the course of the project. Handled team conflicts very well and was always up for Knowledge transfer sessions to the new resources recruited to the project.");
			managerReviewData.setEmployeeName("");
			managerReviewData.validate();
			assertEquals("Employee Name should only have alphabets and it cannot be empty.<br>",
					managerReviewData.getError());

			managerReviewData.setEmployeeName("Chandan Jha");
			managerReviewData.setRating("abc");
			managerReviewData.validate();
			assertEquals("Manager Rating should be a valid number that is greater than 0 and less than 5.<br>",
					managerReviewData.getError());

			managerReviewData.setRating("4.5");
			managerReviewData.setProjectsParticipated(null);
			managerReviewData.validate();
			assertEquals("Atleast one project you participated in should be added.<br>", managerReviewData.getError());

			project3.setEmployeeContribution("20");
			project3.setProjectName("");
			project3.setProjectSize(FinalRatingConstants.ProjectSize.LARGE_SCALE.getValue());

			managerReviewData.setProjectsParticipated(projectParticipationDataList);
			managerReviewData.validate();
			assertEquals("Project Name cannot be empty.<br>", managerReviewData.getError());

			project3.setEmployeeContribution("-20");
			project3.setProjectName("");
			project3.setProjectSize("ABC");

			managerReviewData.setProjectsParticipated(projectParticipationDataList);
			managerReviewData.validate();
			assertEquals(
					"Project Name cannot be empty.<br>Employee contribution should be a valid number that is greater than 0 and less than 100.<br>Project size should be Small scale, Medium scale or Large scale.<br>",
					managerReviewData.getError());
		} catch (Exception e) {
			fail("Exception occured");
			e.printStackTrace();
		}
	}

}
