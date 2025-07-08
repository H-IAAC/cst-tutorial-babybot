# cst-tutorial-babybot
This repository provides a complete and reproducible Docker-based environment for training and integrating infant behavior models using the CST (Cognitive System Toolkit). It was developed as part of a tutorial presented at the ICDL (International Conference on Development and Learning).

## 🧠 Project Overview

This repository provides a complete and reproducible Docker-based environment for training and integrating infant behavior models using the CST (Cognitive System Toolkit). It was developed as part of a tutorial presented at the ICDL (International Conference on Development and Learning).

The environment supports:

Multimodal infant modeling with MIMo

Behavioral learning experiments using the BabyBench benchmark

Integration of cognitive models written in Java (CST) with simulation environments

A lightweight graphical interface accessible via web browser (noVNC)



## ✅ Requirements

This project runs entirely inside a Docker container, so the requirements are minimal on your host system.

---

### 🧱 Host System Requirements

These are required **only on your local machine** to build and run the Docker container.

| Requirement              | Recommended Version                 | Purpose                                                 |
| ------------------------ | ----------------------------------- | ------------------------------------------------------- |
| **Docker**               | 20.10+                              | To build and run the development environment            |
| **Operating System**     | Linux / macOS / Windows (with WSL2) | Linux or WSL2 is recommended for smoother support       |
| **Web Browser**          | Any (Chrome, Firefox, etc.)         | To access the graphical interface via noVNC (port 8080) |
| **Git**                  | 2.x                                 | To clone BabyBench and MIMo repositories (optional)     |
| **CPU with AVX support** | Yes                                 | Recommended for PyTorch performance (MIMo models)       |

---

### 📦 Preinstalled Inside the Docker Container

Everything below is already included in the Dockerfile — no need to install manually:

#### 🔧 Languages and Platforms

* **Python** 3.12 (via Miniconda)
* **Java** OpenJDK 17
* **Gradle** 7.6
* **Maven**
* **CST-CLI** (custom `.deb` package pre-installed)

#### 🧠 Python Libraries

Installed via `requirements.txt` from BabyBench and MIMo:

* `torch`, `numpy`, `matplotlib`, `pandas`
* `gymnasium`, `pygame`, `opencv-python`, `pyyaml`
* `websockify` (for VNC proxy)
* `stable-baselines3` *(can be removed as per your Dockerfile)*

#### 🖥️ GUI and Developer Tools

* **Xfce4** (lightweight desktop environment)
* **x11vnc**, **xvfb**, **noVNC** (browser-based GUI)
* **Geany** (code editor with syntax highlighting)
* **Mousepad** (lightweight text editor)
* **xfce4-terminal** (terminal emulator)

---

### 🧪 Additional Skills (Recommended for Usage)

* 🐍 **Basic Python & Conda**: To activate environments and run scripts
* ☕ **Basic Java**: To compile and run `.java` files
* 🧠 **Interest in Developmental AI**: To benefit from BabyBench simulations
* 🐳 **Basic Docker knowledge**: To build, run, and troubleshoot the container

