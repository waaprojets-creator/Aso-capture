# Wrapper Gradle

Le `gradle-wrapper.jar` est volumineux (~50 KB) et habituellement commit dans le repo, mais pour la simplicité du squelette initial il n'est pas inclus dans ce package ZIP.

**Pour finaliser le wrapper :**

Option 1 — depuis Android Studio :
1. Ouvrir le projet dans Android Studio
2. Le wrapper est généré automatiquement à la première synchronisation Gradle

Option 2 — en ligne de commande (si Gradle est installé localement) :
```bash
gradle wrapper --gradle-version 8.7
```

Cette commande génère :
- `gradlew` (script shell Unix)
- `gradlew.bat` (script Windows)
- `gradle/wrapper/gradle-wrapper.jar`

Une fois fait, ces 3 fichiers doivent être commit dans le repo (sauf `gradle-wrapper.jar` selon les conventions de ton équipe).
