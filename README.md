# Library Management System – Java TP2

A console-based library management application developed as part of a university coursework (TP2). It demonstrates core Object-Oriented Programming concepts in Java: inheritance, polymorphism, encapsulation, exception handling, and file I/O.

## Features

- Manage books (`Livre`), authors (`Auteur`), members (`Adherent`), and loans (`Emprunt`).
- Add/search for books and members.
- Borrow and return books with availability checks.
- Custom exceptions for invalid author codes, missing members, and return errors.
- Persistence using text file storage (save/load books and members to/from `.txt` files).

## OOP Concepts Illustrated

| Concept          | Implementation                                                                                                                                 |
|------------------|------------------------------------------------------------------------------------------------------------------------------------------------|
| **Inheritance**  | `Auteur` and `Adherent` inherit from `Personne` (common attributes: `nom`, `prenom`).                                                          |
| **Polymorphism** | Overridden `toString()` in subclasses; `Bibliotheque` methods work with `Personne` references when needed.                                     |
| **Encapsulation**| Private fields with public getters/setters (e.g., `getCode()`, `setCode()` with validation).                                                    |
| **Abstraction**  | `Personne` can be abstract (if desired), forcing subclasses to implement specific behaviors.                                                   |
| **Exception Handling** | Checked exceptions: `CodeAutException`, `AdherentInexistantException`, `RetourImpossibleException`.                                 |
| **Collections**  | `ArrayList<Livre>`, `ArrayList<Adherent>`, `ArrayList<Emprunt>` for dynamic data management.                                                   |
| **File I/O**     | Read/write books and members to text files using `Files.write()`/`Files.readAllLines()`; copy files to backup directory.                      |

## Casting in Java (Upcasting and Downcasting)

Casting allows us to treat an object of a subclass as an instance of its superclass (upcasting) or to convert a superclass reference back to a subclass reference (downcasting).

### Upcasting (implicit)
Upcasting is always safe and does not require explicit syntax. It is used to achieve polymorphism.

```java
Personne p = new Auteur("Hugo", "Victor", 1234);  // upcasting