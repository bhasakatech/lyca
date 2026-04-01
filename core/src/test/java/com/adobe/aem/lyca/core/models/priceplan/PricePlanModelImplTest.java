package com.adobe.aem.lyca.core.models.priceplan;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class PricePlanModelImplTest {
    private final AemContext context = new AemContext();
    private PricePlanModel pricePlanModel;
    @BeforeEach
    void init() {
        context.addModelsForClasses(PricePlanModelImpl.class);
        context.load().json("/priceplan.json", "/content");
        Resource resource = context.resourceResolver().getResource("/content/priceplan-valid");
        assertNotNull(resource);
        pricePlanModel = resource.adaptTo(PricePlanModel.class);
    }
    @Test
    void testModelNotNull() {
        assertNotNull(pricePlanModel);
    }
    @Test
    void testHeading() {
        assertEquals("Choose Your Plan", pricePlanModel.getPricePlanHeading());
    }
    @Test
    void testMonthlyText() {
        assertEquals("Monthly", pricePlanModel.getPricePlanMonthlyText());
    }
    @Test
    void testYearlyText() {
        assertEquals("Yearly", pricePlanModel.getPricePlanYearlyText());
    }
    @Test
    void testPlanCardsPath() {
        assertEquals("/content/lyca/plans", pricePlanModel.getPlanCardsPath());
    }
    @Test
    void testFindPlan() {
        assertEquals("Find a Plan", pricePlanModel.getFindPlanLabel());
        assertEquals("/content/lyca/find-plan", pricePlanModel.getFindPlanLink());
    }
    @Test
    void testAllPlan() {
        assertEquals("View All Plans", pricePlanModel.getAllPlanLabel());
        assertEquals("/content/lyca/all-plans", pricePlanModel.getAllPlanLink());
    }
    @Test
    void testExportedType() {
        assertEquals("lyca-spa-react/components/priceplan", pricePlanModel.getExportedType());
    }
    @Test
    void testEmptyValues() {
        Resource resource = context.resourceResolver().getResource("/content/priceplan-empty");
        assertNotNull(resource);
        PricePlanModel model = resource.adaptTo(PricePlanModel.class);
        assertNotNull(model);
        assertEquals("", model.getPricePlanHeading());
        assertEquals("", model.getPricePlanMonthlyText());
        assertEquals("", model.getPricePlanYearlyText());
        assertEquals("", model.getPlanCardsPath());
        assertEquals("", model.getFindPlanLabel());
        assertEquals("", model.getFindPlanLink());
        assertEquals("", model.getAllPlanLabel());
        assertEquals("", model.getAllPlanLink());
        assertEquals("lyca-spa-react/components/priceplan", model.getExportedType());
    }
    @Test
    void testPartialValues() {
        Resource resource = context.resourceResolver().getResource("/content/priceplan-partial");
        assertNotNull(resource);
        PricePlanModel model = resource.adaptTo(PricePlanModel.class);
        assertNotNull(model);
        assertEquals("Plans", model.getPricePlanHeading());
        assertEquals("Monthly", model.getPricePlanMonthlyText());
        assertNull(model.getPricePlanYearlyText());
        assertNull(model.getPlanCardsPath());
        assertNull(model.getFindPlanLabel());
        assertNull(model.getFindPlanLink());
        assertNull(model.getAllPlanLabel());
        assertNull(model.getAllPlanLink());
        assertEquals("lyca-spa-react/components/priceplan", model.getExportedType());
    }
    @Test
    void testInvalidValues() {
        Resource resource = context.resourceResolver().getResource("/content/priceplan-invalid");
        assertNotNull(resource);
        PricePlanModel model = resource.adaptTo(PricePlanModel.class);
        assertNotNull(model);
        assertEquals("123", model.getPricePlanHeading());
        assertEquals("true", model.getPricePlanMonthlyText());
        assertEquals("456", model.getPlanCardsPath());
        assertEquals("false", model.getAllPlanLabel());
        assertNull(model.getPricePlanYearlyText());
        assertNull(model.getAllPlanLink());
        assertNull(model.getFindPlanLabel());
        assertNull(model.getFindPlanLink());
        assertEquals("lyca-spa-react/components/priceplan", model.getExportedType());
    }
}