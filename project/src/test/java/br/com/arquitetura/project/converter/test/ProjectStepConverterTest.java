package br.com.arquitetura.project.converter.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.arquitetura.project.converter.ProjectStepConverter;
import br.com.arquitetura.project.data.ProjectStepData;
import br.com.arquitetura.project.data.StepData;
import br.com.arquitetura.project.entity.ProjectStep;
import br.com.arquitetura.project.enumeration.StepStatusEnum;

public class ProjectStepConverterTest {

	@Test
	public void testProjectStepsSizeAndProject() {
		List<ProjectStepData> projectStepsData = new ArrayList<>();
		
		projectStepsData.add(new ProjectStepData(null, 1L, new StepData(), StepStatusEnum.AGUARDANDO_INICIO));
		projectStepsData.add(new ProjectStepData(null, 1L, new StepData(), StepStatusEnum.AGUARDANDO_INICIO));
		
		List<ProjectStep> projectSteps = ProjectStepConverter.convertToProjectStep(projectStepsData);
		
		assertEquals(2, projectSteps.size());
		assertEquals("1", projectSteps.get(0).getProject().getUid().toString());
	}

}
