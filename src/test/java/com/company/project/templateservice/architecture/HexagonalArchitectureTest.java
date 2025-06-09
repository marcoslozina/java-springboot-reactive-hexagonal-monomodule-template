package com.company.project.templateservice.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;

class HexagonalArchitectureTest {

  private final JavaClasses importedClasses =
      new ClassFileImporter()
          .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
          .importPackages("com.company.templateservice");

  @Test
  void domainShouldNotDependOnApplicationOrAdapters() {
    ArchRule rule =
        ArchRuleDefinition.noClasses()
            .that()
            .resideInAPackage("..domain..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..application..", "..adapters..")
            .allowEmptyShould(true); // <- evita fallo si no hay clases que cumplan
    rule.check(importedClasses);
  }

  @Test
  void applicationShouldNotDependOnAdapters() {
    ArchRule rule =
        ArchRuleDefinition.noClasses()
            .that()
            .resideInAPackage("..application..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..adapters..")
            .allowEmptyShould(true);
    rule.check(importedClasses);
  }

  @Test
  void adaptersShouldOnlyDependOnApplicationOrDomain() {
    ArchRule rule =
        ArchRuleDefinition.classes()
            .that()
            .resideInAPackage("..adapters..")
            .should()
            .onlyDependOnClassesThat()
            .resideInAnyPackage("..application..", "..domain..", "java..")
            .allowEmptyShould(true);
    rule.check(importedClasses);
  }
}
