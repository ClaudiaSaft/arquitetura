package br.com.arquitetura.projectStep;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.arquitetura.converter.ProjectStepConverter;
import br.com.arquitetura.data.ProjectStepData;
import br.com.arquitetura.entity.ProjectStep;

public class ProjectStepTest {

	@Test
	public void test() {
		
		List<ProjectStepData> steps = new ArrayList<>();
		
		ProjectStepData projectStepData1 = new ProjectStepData();
		projectStepData1.setUid(1L);
		projectStepData1.setUidStep(1L);
		projectStepData1.setUidProject(1L);
		
		steps.add(projectStepData1);
		
		ProjectStepData projectStepData2 = new ProjectStepData();
		projectStepData2.setUid(2L);
		projectStepData2.setUidStep(2L);
		projectStepData2.setUidProject(1L);

		ProjectStepData projectStepData3 = new ProjectStepData();
		projectStepData3.setUid(3L);
		projectStepData3.setUidStep(3L);
		projectStepData3.setUidProject(1L);
		
		List<ProjectStepData> subSteps1 = new ArrayList<>();
		subSteps1.add(projectStepData2);
		subSteps1.add(projectStepData3);
		projectStepData1.setSubProjectSteps(subSteps1);

		
		
		ProjectStepData projectStepData4 = new ProjectStepData();
		projectStepData4.setUid(4L);
		projectStepData4.setUidStep(4L);
		projectStepData4.setUidProject(1L);
		
		List<ProjectStepData> subSteps3 = new ArrayList<>();
		subSteps3.add(projectStepData4);
		projectStepData3.setSubProjectSteps(subSteps3);
		
		
		ProjectStepData projectStepData5 = new ProjectStepData();
		projectStepData5.setUid(5L);
		projectStepData5.setUidStep(5L);
		projectStepData5.setUidProject(1L);
		
		steps.add(projectStepData5);
		
		ProjectStepData projectStepData6 = new ProjectStepData();
		projectStepData6.setUid(6L);
		projectStepData6.setUidStep(6L);
		projectStepData6.setUidProject(1L);
		
		List<ProjectStepData> subSteps5 = new ArrayList<>();
		subSteps5.add(projectStepData6);
		projectStepData5.setSubProjectSteps(subSteps5);
		
		System.out.println("Listar tudo Data");
		listarTudo(steps, "");
		
		List<ProjectStep> projectSteps = ProjectStepConverter.convertToProjectStep(steps);
		Assert.assertEquals(2, projectSteps.size());
		
		System.out.println("\n\nListar tudo Objeto");
		listarTudo1(projectSteps, "");
		
		Assert.assertEquals(2, getSubProjectStepsFirstOwner(projectSteps).size());
		Assert.assertEquals(0, getSubProjectStepsFirstOwner(projectSteps).get(0).getSubProjectSteps().size());
		Assert.assertEquals(1, getSubProjectStepsFirstOwner(projectSteps).get(1).getSubProjectSteps().size());
		Assert.assertEquals(1, getSubProjectStepsFirstOwner(projectSteps).get(1).getSubProjectSteps().size());
		Assert.assertEquals(1, getSubProjectStepsSecondOwner(projectSteps).size());
		Assert.assertEquals(0, getSubProjectStepsSecondOwner(projectSteps).get(0).getSubProjectSteps().size());
	}

	private List<ProjectStep> getSubProjectStepsSecondOwner(List<ProjectStep> projectSteps) {
		return projectSteps.get(1).getSubProjectSteps();
	}

	private List<ProjectStep> getSubProjectStepsFirstOwner(List<ProjectStep> projectSteps) {
		return projectSteps.get(0).getSubProjectSteps();
	}

	private void listarTudo(List<ProjectStepData> steps, String next) {
		for (ProjectStepData projectStepData : steps) {
			System.out.println(next + " uid: " + projectStepData.getUid() + " project: " + projectStepData.getUidProject() + " step: " + projectStepData.getUidStep());
			listarTudo(projectStepData.getSubProjectSteps(), next + " -");
		}
	}

	private void listarTudo1(List<ProjectStep> steps, String next) {
		for (ProjectStep projectStepData : steps) {
			System.out.println(next + " uid: " + projectStepData.getUid() + 
					" project: " + projectStepData.getProject() == null ? null : projectStepData.getProject().getUid() + 
					" step: " + projectStepData.getStep().getUid() + 
					" owner: " + projectStepData.getProjectStepOwner());
			listarTudo1(projectStepData.getSubProjectSteps(), next + " -");
		}
	}

}
