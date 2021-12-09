package com.asdc.payroll_management.appraisal.final_rating;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.asdc.payroll_management.DataBaseCache.Appraisal;
import com.asdc.payroll_management.DataBaseCache.AppraisalCache;
import com.asdc.payroll_management.DataBaseCache.EmployeeCache;
import com.asdc.payroll_management.appraisal.manager.ManagerReviewData;
import com.asdc.payroll_management.appraisal.manager.ProjectParticipationData;
import com.asdc.payroll_management.appraisal.self.SelfReviewData;

public class EmployeeAppraisalRatingConcreteFactory extends RatingFactory {

	private double calculateFinalRating(ManagerReviewData managerReviewData, SelfReviewData selfReviewData) {
		double managerRating = Double.valueOf(managerReviewData.getRating());
		double managerCommunicationRating = Double.valueOf(managerReviewData.getRating());
		double selfRating = Double.valueOf(selfReviewData.getRating());
		double projectsContribution = getProjectsCompleted(managerReviewData.getProjectsParticipated());
		double projectsContributionRating = convertContributionToRating(projectsContribution);
		double technologiesLearned = Double.valueOf(selfReviewData.getTechnologiesLearnt().size());
		double technologiesLearnedRating = convertTechnologiesLearnedToRating(technologiesLearned);
		return new BigDecimal(String.valueOf((managerRating * 0.25) + (managerCommunicationRating * 0.05)
				+ (selfRating * 0.05) + (projectsContributionRating * 0.60) + (technologiesLearnedRating * 0.05)))
						.setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	private double convertTechnologiesLearnedToRating(double technologiesLearned) {
		double technologyRating = 0d;
		for (FinalRatingConstants.TechnologiesToRating rating : FinalRatingConstants.TechnologiesToRating.values()) {
			if (rating.getMin() <= technologiesLearned && rating.getMax() >= technologiesLearned) {
				if ((rating.name()).equals(FinalRatingConstants.TechnologiesToRating.ONE.name())) {
					double oneProjectRating = 1 / (rating.getMax() - rating.getMin());
					if (oneProjectRating == 1d) {
						technologyRating = 1;
					} else {
						technologyRating = 1 + oneProjectRating * (technologiesLearned - rating.getMin());
					}
				} else if ((rating.name()).equals(FinalRatingConstants.TechnologiesToRating.TWO.name())) {
					double oneProjectRating = 1 / (rating.getMax() - rating.getMin());
					if (oneProjectRating == 1d) {
						technologyRating = 2;
					} else {
						technologyRating = 2 + oneProjectRating * (technologiesLearned - rating.getMin());
					}
				} else if ((rating.name()).equals(FinalRatingConstants.TechnologiesToRating.THREE.name())) {
					double oneProjectRating = 1 / (rating.getMax() - rating.getMin());
					if (oneProjectRating == 1d) {
						technologyRating = 3;
					} else {
						technologyRating = 3 + oneProjectRating * (technologiesLearned - rating.getMin());
					}
				} else if ((rating.name()).equals(FinalRatingConstants.TechnologiesToRating.FOUR.name())) {
					double oneProjectRating = 1 / (rating.getMax() - rating.getMin());
					if (oneProjectRating == 1d) {
						technologyRating = 4;
					} else {
						technologyRating = 4 + oneProjectRating * (technologiesLearned - rating.getMin());
					}
				} else {
					return 5d;
				}
			}
		}
		return technologyRating;
	}

	private double convertContributionToRating(double projectsContribution) {
		double projectRating = 0d;
		for (FinalRatingConstants.ContributionToRating rating : FinalRatingConstants.ContributionToRating.values()) {
			if (rating.getMin() <= projectsContribution && rating.getMax() >= projectsContribution) {
				if ((rating.name()).equals(FinalRatingConstants.ContributionToRating.ONE.name())) {
					double oneProjectRating = 1 / (rating.getMax() - rating.getMin());
					if (oneProjectRating == 1d) {
						projectRating = 1;
					} else {
						projectRating = 1 + oneProjectRating * (projectsContribution - rating.getMin());
					}
				} else if ((rating.name()).equals(FinalRatingConstants.ContributionToRating.TWO.name())) {
					double oneProjectRating = 1 / (rating.getMax() - rating.getMin());
					if (oneProjectRating == 1d) {
						projectRating = 2;
					} else {
						projectRating = 2 + oneProjectRating * (projectsContribution - rating.getMin());
					}
				} else if ((rating.name()).equals(FinalRatingConstants.ContributionToRating.THREE.name())) {
					double oneProjectRating = 1 / (rating.getMax() - rating.getMin());
					if (oneProjectRating == 1d) {
						projectRating = 3;
					} else {
						projectRating = 3 + oneProjectRating * (projectsContribution - rating.getMin());
					}
				} else if ((rating.name()).equals(FinalRatingConstants.ContributionToRating.FOUR.name())) {
					double oneProjectRating = 1 / (rating.getMax() - rating.getMin());
					if (oneProjectRating == 1d) {
						projectRating = 4;
					} else {
						projectRating = 4 + oneProjectRating * (projectsContribution - rating.getMin());
					}
				} else {
					return 5d;
				}
			}
		}
		return projectRating;
	}

	private double getProjectsCompleted(List<ProjectParticipationData> projectsParticipated) {
		double projectContribution = 0d;
		for (ProjectParticipationData project : projectsParticipated) {
			if (project.getProjectSize().equals(FinalRatingConstants.ProjectSize.LARGE_SCALE.getValue())) {
				projectContribution = projectContribution + (Double.valueOf(project.getEmployeeContribution())
						* FinalRatingConstants.LARGE_SCALE_TO_MEDIUM_SCALE_RATIO
						* FinalRatingConstants.MEDIUM_SCALE_TO_SMALL_SCALE_RATIO);
			} else if (project.getProjectSize().equals(FinalRatingConstants.ProjectSize.MEDIUM_SCALE.getValue())) {
				projectContribution = projectContribution + (Double.valueOf(project.getEmployeeContribution())
						* FinalRatingConstants.MEDIUM_SCALE_TO_SMALL_SCALE_RATIO);
			} else {
				projectContribution = projectContribution + (Double.valueOf(project.getEmployeeContribution()));
			}
		}
		return projectContribution / 100;
	}

	private List<ProjectParticipationData> getProjects(String projectsRating) {
		if (projectsRating == null || projectsRating.isBlank()) {
			return new ArrayList<ProjectParticipationData>();
		}
		String[] projects = projectsRating.substring(1, projectsRating.length() - 1).split(",");
		List<ProjectParticipationData> projectparticipationDataList = new ArrayList<ProjectParticipationData>();
		for (String project : projects) {
			ProjectParticipationData projectParticipationData = new ProjectParticipationData();
			String[] projectComponents = project.split("\\|\\|");
			for (String projectComponent : projectComponents) {
				String[] keyValues = projectComponent.split(":");
				if (keyValues[0].trim().equals("projectName")) {
					projectParticipationData.setProjectName(keyValues[1].trim());
				} else if (keyValues[0].trim().equals("projectSize")) {
					projectParticipationData.setProjectSize(keyValues[1].trim());
				} else if (keyValues[0].trim().equals("employeeContribution")) {
					projectParticipationData.setEmployeeContribution(keyValues[1].trim());
				} else if (keyValues[0].trim().equals("error")) {
					projectParticipationData.validate();
				}
			}
			projectparticipationDataList.add(projectParticipationData);
		}
		return projectparticipationDataList;
	}

	private List<String> getAsList(String listAsString) {
		if (listAsString == null || listAsString.isBlank()) {
			return new ArrayList<String>();
		}
		String[] listElements = listAsString.substring(1, listAsString.length() - 1).split(",");
		List<String> convertedList = new ArrayList<String>();
		for (String listElement : listElements) {
			convertedList.add(listElement.trim());
		}
		return convertedList;
	}

	@Override
	public Double getFinalRating(String employeeCode) {
		Appraisal appraisal = AppraisalCache.getInstance().getIndividualAppraisals(employeeCode);
		if (appraisal == null) {
			return null;
		}
		SelfReviewData selfReviewData = getSelfReviewFromAppraisal(appraisal);
		ManagerReviewData managerReviewData = getManagerReviewFromAppraisal(appraisal);

		return getFinalRating(employeeCode, appraisal, selfReviewData, managerReviewData);
	}

	private Double getFinalRating(String employeeCode, Appraisal appraisal, SelfReviewData selfReviewData,
			ManagerReviewData managerReviewData) {
		if (appraisal.getFinalRating() == null || appraisal.getFinalRating().isBlank()
				|| appraisal.getFinalRating().equals("null")) {
			return calculateFinalRating(managerReviewData, selfReviewData);
		}
		return Double.valueOf(appraisal.getFinalRating());
	}

	private ManagerReviewData getManagerReviewFromAppraisal(Appraisal appraisalData) {
		ManagerReviewData managerReviewData = new ManagerReviewData();
		managerReviewData.setComments(appraisalData.getManagerComments());
		managerReviewData.setCommunicationSkillsRating(appraisalData.getCommunicationRating());
		managerReviewData.setEmployeeID(appraisalData.getEmployeeID());
		managerReviewData.setEmployeeName(
				EmployeeCache.getInstance().getEmployee(appraisalData.getEmployeeID()).getEmployeeName());
		managerReviewData.setProjectsParticipated(getProjects(appraisalData.getProjectsRating()));
		managerReviewData.setRating(appraisalData.getManagerRating());
		return managerReviewData;
	}

	private SelfReviewData getSelfReviewFromAppraisal(Appraisal appraisalData) {
		SelfReviewData selfReviewData = new SelfReviewData();
		selfReviewData.setComments(appraisalData.getEployeeComments());
		selfReviewData.setEmployeeID(appraisalData.getEmployeeID());
		selfReviewData.setEmployeeName(
				EmployeeCache.getInstance().getEmployee(appraisalData.getEmployeeID()).getEmployeeName());
		selfReviewData.setProjectsParticipated(getAsList(appraisalData.getEmployeeProjects()));
		selfReviewData.setRating(appraisalData.getEmployeeRating());
		selfReviewData.setTechnologiesLearnt(getAsList(appraisalData.getTechnologiesLearnt()));
		return selfReviewData;
	}

}
