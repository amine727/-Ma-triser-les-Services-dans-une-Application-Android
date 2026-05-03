# 📱 LAB 16 – Maîtriser les Services dans une Application Android

## 🎯 Objectif

Ce lab a pour objectif de comprendre et implémenter les **Services Android**, en particulier :

* ✅ Foreground Service
* ✅ Notification persistante
* ✅ Bound Service (liaison avec Activity)
* ✅ Gestion du cycle de vie d’un service

---

## 🛠️ Technologies utilisées

* Java
* Android Studio
* Android SDK (API 24+)

---

## 📸 Résultat de l'application

<img width="428" height="569" alt="1" src="https://github.com/user-attachments/assets/eb1c8427-6452-44eb-84ca-8fc665301451" />

> 💡 L’application affiche un chronomètre qui continue de fonctionner même en arrière-plan grâce au Foreground Service.

---

## ⚙️ Fonctionnalités

* ▶️ Démarrer le chronomètre
* ⏹️ Arrêter le chronomètre
* 🔔 Notification persistante avec le temps en direct
* 🔄 Exécution en arrière-plan (Foreground Service)
* 🔗 Communication Activity ↔ Service (Bound Service)

---

## 🧩 Architecture

### 🔹 MainActivity

* Lance et arrête le service
* Se connecte au service (Bound Service)
* Met à jour l’interface utilisateur

### 🔹 ChronometreService

* Gère le chronomètre
* Met à jour la notification
* Exécute une tâche en arrière-plan

---

## 📂 Structure du projet

```
com.example.servicechronometrejava
│
├── MainActivity.java
├── ChronometreService.java
└── res/
    └── layout/activity_main.xml
```

---

## 🚀 Exécution

1. Ouvrir le projet dans Android Studio
2. Lancer un émulateur ou connecter un appareil
3. Cliquer sur ▶️ Run
4. Tester :

   * Cliquer sur **DÉMARRER SERVICE**
   * Observer la notification
   * Quitter l’app → le chrono continue
   * Cliquer sur **ARRÊTER SERVICE**

---

## ⚠️ Permissions requises

```xml
<uses-permission android:name="android.permission.POST_NOTIFICATIONS"/>
<uses-permission android:name="android.permission.FOREGROUND_SERVICE"/>
<uses-permission android:name="android.permission.FOREGROUND_SERVICE_DATA_SYNC"/>
```

---

## 📌 Concepts clés

* 🔹 **Foreground Service** : service avec notification obligatoire
* 🔹 **Bound Service** : communication avec l’Activity
* 🔹 **Notification Channel** : obligatoire Android 8+
* 🔹 **Handler / Executor** : exécution périodique

---

## 🎓 Conclusion

Ce lab permet de maîtriser les services Android et leur utilisation dans des applications nécessitant un traitement en arrière-plan, comme les chronomètres, téléchargements ou musique.

---

