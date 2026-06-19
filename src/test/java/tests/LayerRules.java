package tests;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import cl.ucn.adhoc.repositorio.RepositorioTarea;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = {"consola", "dominio", "repositorio", "tests"})
public class LayerRules {

    @ArchTest
    static final ArchRule dominio_should_not_depend_on_anything =
            noClasses().that().resideInAPackage("..dominio..")
                    .should().dependOnClassesThat()
                    .resideInAnyPackage("..consola..", "..repositorio..", "..tests..", "java..", "javax..")
                    .because("La capa dominio no debe depender de ninguna capa.");

    @ArchTest
    static final ArchRule nothing_should_depend_on_dominio =
            noClasses().that().resideInAnyPackage("..consola..", "..repositorio..", "..tests..", "java..", "javax..")
                    .should().dependOnClassesThat()
                    .resideInAPackage("..dominio..")
                    .because("Ninguna capa debe depender de la capa dominio.");

//    @ArchTest
//    static final ArchRule servicio_should_not_depend_on_repositorio_classes =
//            noClasses().that().resideInAPackage("..servicio..")
//                    .should().dependOnClassesThat()
//                    .areAssignableTo(RepositorioTarea.class)
//                    .andShould().notBeInterfaces()
//                    .because("La capa servicio debe depender solo de la interfaz RepositorioTarea, no de sus implementaciones concretas");

    @ArchTest
    static final ArchRule servicio_should_not_depend_on_RepositorioTareaImpl =
            classes().that().resideInAPackage("..servicio..")
                    .should().onlyDependOnClassesThat()
                    .areAssignableTo(RepositorioTarea.class)
                    .because("La capa servicio no debe depender de implementaciones concretas del repositorio como RepositorioTareaImpl");
}