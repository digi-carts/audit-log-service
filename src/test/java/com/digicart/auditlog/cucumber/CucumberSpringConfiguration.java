package com.digicart.auditlog.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import com.digicart.auditlog.exception.GlobalExceptionHandler;
import com.digicart.auditlog.controller.HealthController;
import com.digicart.auditlog.controller.AuditLogController;
import com.digicart.auditlog.service.AuditLogService;

@CucumberContextConfiguration
@WebMvcTest(controllers = { HealthController.class, AuditLogController.class })
@AutoConfigureMockMvc(addFilters = false)
@Import(GlobalExceptionHandler.class)
public class CucumberSpringConfiguration {
    @MockBean
    AuditLogService auditLogService;

}
