<h2>ANOTAIONS SPRING</h2>
**@Builder**
A anotação @Builder é usada para gerar automaticamente um padrão de projeto de criação de objetos chamado "Builder
Pattern" para uma classe.
Quando você aplica @Builder a uma classe, o Lombok gera um construtor privado na classe chamado Builder, que permite
criar instâncias da classe de forma mais fluente e legível.
Isso é especialmente útil quando você tem muitos campos em uma classe e deseja criar objetos dessa classe de forma mais
concisa e sem a necessidade de chamar um grande construtor com muitos argumentos.

**Exemplo:**

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {
private String name;
private int age;
}

// Uso
Person person = Person.builder()
.name("Alice")
.age(30)
.build();

**@SuperBuilder:**
@SuperBuilder é uma extensão do @Builder.
Quando aplicado a uma classe, ele gera um construtor na classe filho que chama automaticamente o construtor gerado pelo
@Builder da classe pai.
Isso é útil quando você herda de uma classe que possui um construtor gerado pelo @Builder e deseja criar instâncias da
classe filha, herdando os recursos de criação de objetos da classe pai.
**Exemplo:**
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@Builder
public class Person {
private String name;
private int age;
}

@SuperBuilder
public class Employee extends Person {
private String employeeId;
}

// Uso
Employee employee = Employee.builder()
.name("Bob")
.age(35)
.employeeId("12345")
.build();

<h3>Atalhos intellij</h3>
ctrl + shift + up or down -> **move lines**

crtl+ alt + n -> **find for files**