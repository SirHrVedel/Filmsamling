# FURPS- krav til filmsamling
## F- Functionality
**Krav:** Brugeren skal kunne oprette, søge efter, redigere og slette film.

**Opfyldt:** Delvist.

**Hvor:** `MovieCollection.java` indeholder metoderne `addMovie()`, `searchMovies()`, `editMovie()` og `deleteMovie()`.

---

## U – Usability

**Krav:** Programmet skal vise tydelige beskeder, så brugeren ved, hvilke oplysninger der skal indtastes.

**Opfyldt:** Ja.

**Hvor:** `UserInterface.java`, metoden `startProgram()`.

---

## R – Reliability

**Krav:** Programmet skal håndtere ugyldig indtastning af tal, så brugeren kan prøve igen.

**Opfyldt:** Ja.

**Hvor:** `UserInterface.java`, metoden `awaitInputInt()`.

---

## P – Performance

**Krav:** Programmet skal kunne søge efter film ud fra en del af titlen.

**Opfyldt:** Ja.

**Hvor:** `MovieCollection.java`, metoden `searchMovies()`.

---

## S – Supportability

**Krav:** Koden skal være opdelt i klasser med forskellige ansvarsområder, så den er nemmere at vedligeholde.

**Opfyldt:** Ja.

**Hvor:** `Movie.java`, `MovieCollection.java`, `Controller.java` og `UserInterface.java`.
