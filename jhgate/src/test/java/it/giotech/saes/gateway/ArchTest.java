package it.giotech.saes.gateway;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("it.giotech.saes.gateway");

        noClasses()
            .that()
                .resideInAnyPackage("it.giotech.saes.gateway.service..")
            .or()
                .resideInAnyPackage("it.giotech.saes.gateway.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..it.giotech.saes.gateway.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
