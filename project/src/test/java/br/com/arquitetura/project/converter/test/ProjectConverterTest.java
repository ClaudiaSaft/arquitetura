package br.com.arquitetura.project.converter.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.arquitetura.project.converter.ProjectConverter;
import br.com.arquitetura.project.data.ProjectData;
import br.com.arquitetura.project.data.ProjectStepData;
import br.com.arquitetura.project.data.StepData;
import br.com.arquitetura.project.entity.Project;
import br.com.arquitetura.project.enumeration.ProjectStatusEnum;
import br.com.arquitetura.project.enumeration.StepStatusEnum;

public class ProjectConverterTest {

	@Test
	public void testProjectOnlyWithRequiredFields() {
		ProjectData projectData = new ProjectData.Builder("Project Name", 1L, 1L)
				.build();
		
		Project project = ProjectConverter.convertToProject(projectData);
		assertEquals("Project Name", project.getName());
		assertEquals("1", project.getType().getUid().toString());
		assertEquals("1", project.getSubType().getUid().toString());
	}

	@Test
	public void testProjectWithAllFieldsButProjectSteps() {
		ProjectData projectData = new ProjectData.Builder("Project Name", 1L, 1L)
				.uidArchitect(1L)
				.uidCustomer(1L)
				.description("Project description")
				.status(ProjectStatusEnum.AGUARDANDO_INICIO)
				.build();
		
		Project project = ProjectConverter.convertToProject(projectData);
		assertEquals("Project description", project.getDescription());
		assertEquals("1", project.getArchitect().getUid().toString());
		assertEquals("1", project.getCustomer().getUid().toString());
		assertEquals(ProjectStatusEnum.AGUARDANDO_INICIO, project.getStatus());
	}

	@Test
	public void testProjectWithProjectSteps() {
		
		StepData stepData1 = new StepData(1L, "Step Data 1 description", StepStatusEnum.AGUARDANDO_INICIO);
		StepData stepData2 = new StepData(2L, "Step Data 2 description", StepStatusEnum.AGUARDANDO_INICIO);
		StepData stepData3 = new StepData(3L, "Step Data 3 description", StepStatusEnum.AGUARDANDO_INICIO);
		StepData stepData4 = new StepData(4L, "Step Data 4 description", StepStatusEnum.AGUARDANDO_INICIO);
		StepData stepData5 = new StepData(7L, "Step Data 5 description", StepStatusEnum.AGUARDANDO_INICIO);
		StepData stepData6 = new StepData(6L, "Step Data 6 description", StepStatusEnum.AGUARDANDO_INICIO);
		StepData stepData7 = new StepData(7L, "Step Data 7 description", StepStatusEnum.AGUARDANDO_INICIO);
		
		stepData1.addSubProjectStep(stepData2);
			stepData2.addSubProjectStep(stepData3);
				stepData3.addSubProjectStep(stepData6);
			stepData2.addSubProjectStep(stepData4);
		
		stepData5.addSubProjectStep(stepData7);
		
		List<ProjectStepData> projectStepsData = new ArrayList<>();
		projectStepsData.add(new ProjectStepData(null, null, stepData1, StepStatusEnum.AGUARDANDO_INICIO));
		projectStepsData.add(new ProjectStepData(null, null, stepData5, StepStatusEnum.AGUARDANDO_INICIO));

		
		ProjectData projectData = new ProjectData.Builder("Project Name", 1L, 1L)
				.projectStepsData(projectStepsData)
				.build();
		
		assertEquals(2, projectData.getProjectStepsData().size());
		assertEquals("Step Data 1 description", projectData.getProjectStepsData().get(0).getStepData().getDescription());
		assertEquals(1, projectData.getProjectStepsData().get(0).getStepData().getSubStepsData().size());
		assertEquals(2, projectData.getProjectStepsData().get(0).getStepData().getSubStepsData().get(0).getSubStepsData().size());
		assertEquals("Step Data 5 description", projectData.getProjectStepsData().get(1).getStepData().getDescription());
	}

}
