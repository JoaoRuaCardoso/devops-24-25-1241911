# CA2 Part1: Virtualization with Vagrant: Technical Report

**Author:** João Cardoso

**Date:** 04/04/2025

**Discipline:** DevOps

**Program:** SWitCH DEV

**Institution:** Instituto Superior de Engenharia/ Instituto Politécnico do Porto

## Table of Contents

- [Introduction](#introduction)
- [Create a Virtual Machine](#create-a-virtual-machine)
- [Configure Network and Services](#configure-network-and-services)
- [Clone the Repository](#clone-the-repository)
- [Set Up Development Environment](#set-up-development-environment)
- [Run the spring boot tutorial basic project - ca1 part1](#run-the-spring-boot-tutorial-basic-project---ca1-part1)
- [Execute the gradle_basic_demo project - ca1 part2](#execute-the-gradle_basic_demo-project---ca1-part2)
- [Execute the gradle_basic_demo project - ca1 part3](#execute-the-gradle_basic_demo-project---ca1-part3)
- [Conclusion](#conclusion)

## Introduction

This report covers **Class Assignment 2 - Part 1**, focusing on virtualization with VirtualBox in the DevOps curriculum. 
The goal was to gain practical experience in setting up and managing virtual environments for modern software development.

I explain how I created and configured a virtual machine, set up the development environment, and ran different projects.

## Create a Virtual Machine

- The process began with downloading and installing VirtualBox from its official site.
- I opened VirtualBox, clicked New, named the VM, and chose the type and version to match the target OS.
- I assigned sufficient memory for smooth performance and created a virtual hard disk to meet the VM's needs.
- In the storage settings, I mounted the OS ISO to the virtual CD/DVD drive, started the VM, and installed the OS.
- I adjusted the VM settings for the Ubuntu 18.04 minimal installation, linking it to the minimal CD. I allocated 8000 MB of RAM and set Network Adapter 1 to NAT for internet access, while Adapter 2 (vboxnet0) enabled isolated host communication.

## Configure Network and Services

After setting up the VM, I configured the network and essential services to improve functionality and accessibility.

- I accessed the VirtualBox Host Network Manager via `File` -> `Host Network Manager`.
- I clicked `Create`, adding a new Host-only network and allowing me to specify a name for the network within the virtual machine's network settings.
- After setting up the Host-only Adapter (vboxnet0), I checked the IP range `192.168.56.1/24` and assigned `192.168.56.5 to the VM's` second adapter within the subnet.
- After booting the VM, I updated the package repositories with `sudo apt update`.
- I installed the network tools package with `sudo apt install net-tools` to facilitate network configuration.
- I installed the network tools package with sudo apt install net-tools for network configuration.
  To set the IP, I edited the network config file using sudo `nano /etc/netplan/01-netcfg.yaml` and applied the required settings:

[![image.png](https://i.postimg.cc/QtyLbXM4/image.png)](https://postimg.cc/mzC6ZWCC)

- I applied the changes using `sudo netplan apply`.
- To enable remote management, I installed and configured OpenSSH with `sudo apt install openssh-server`,
  enabled password authentication by uncommenting `PasswordAuthentication yes` in `/etc/ssh/sshd_config` and restarted SSH with sudo service ssh restart.

## Clone the Repository

To clone my repository in the VM, I first set up secure SSH access to GitHub.

- I generated an SSH key pair with:

```bash
ssh-keygen -t ed25519 -C "myGitHubMail@xxx.com"
```

- To add the new SSH key to GitHub, I first displayed its content with:

```bash
cat ~/.ssh/id_ed25519.pub
```

- I logged into GitHub, went to Settings → SSH and GPG keys, clicked New SSH key, pasted the key, and saved it to enable secure VM authentication.
- With SSH configured, I cloned my repository into the desired directory within the VM using the following command:

```bash
git clone git@github.com:userName/repositoryName.git
```

## Set Up Development Environment

After configuring the VM and verifying network access, I installed the required project tools.

- I started by updating and upgrading packages to keep the VM software up to date using:

```bash
sudo apt update
sudo apt upgrade
```

- Next, I installed Git for version control with:

```bash
sudo apt install git
```

- I installed the JDK and JRE for Java projects with:

```bash
sudo apt install openjdk-17-jdk openjdk-17-jre
```

- Next, I installed Maven for dependency management and building Java projects with:

```bash
sudo apt install maven
```

- Installing Gradle required a few extra steps due to its packaging:

```bash
wget https://services.gradle.org/distributions/gradle-8.12-bin.zip
sudo mkdir /opt/gradle
sudo unzip -d /opt/gradle gradle-8.12-bin.zip
```

- To ensure Gradle could be executed from anywhere, I added its bin directory to the system PATH by modifying the `.bashrc file`:

```bash
echo "export GRADLE_HOME=/opt/gradle/gradle-8.12" >> ~/.bashrc
echo "export PATH=\$GRADLE_HOME/bin:\$PATH" >> ~/.bashrc
source ~/.bashrc
```

These installations provided the VM with the necessary tools to effectively build and manage Java applications, 
allowing me to proceed with executing and testing the projects.
To verify that everything was installed correctly, I checked their versions using the following commands:

```bash
git --version
java --version
mvn --version
gradle --version
```



## Run the spring boot tutorial basic project - ca1 part1

In this part, I ran the **Spring Boot tutorial basic** project, a prerequisite from earlier assignments. The objective was to build and execute it within the configured virtual machine.

1. I moved to the basic directory, which holds the Spring Boot project files.
2. To start the application, I ran the following command in the terminal inside the project folder:

```bash
./mvnw spring-boot:run
```

3. To ensure the application was accessible from external devices such as the host machine or other devices on the same
   network, I specified the VM's IP address when accessing it. To determine the IP address, I used the `ifconfig`
   command. Here is the URL I used to access the application:

```
http://192.168.56.5:8080/
```


The application launched successfully, confirming the backend was working and Spring Boot was serving content correctly. 
I took a screenshot of the landing page to document the setup and execution.

[![image.png](https://i.postimg.cc/bwjCBb8n/image.png)](https://postimg.cc/KRJDK11c)

## Execute the gradle_basic_demo project - ca1 part2

This section outlines the steps to build and run the `gradle_basic_demo` project, which needed execution in both the virtual machine and the host machine.

1. I moved to the gradle_basic_demo directory in the virtual machine and built the project using:

```bash
./gradlew build
```
[![image.png](https://i.postimg.cc/R0yzpBQh/image.png)](https://postimg.cc/06Y3rh41)

2. Afterwards in my VM terminal i ran the server using the command bellow :

```bash
./gradlew runServer
```
[![image.png](https://i.postimg.cc/vHyTQrRh/image.png)](https://postimg.cc/xqsn5b1N)

3. Because the VM used Ubuntu Server without a GUI, running the chat client there wasn't an option. To bypass this, 
I opened a terminal on my host machine, navigated to the `gradle_basic_demo` directory (after cloning it), 
and launched the client with the command below. This allowed it to connect to the server in the VM by specifying the VM's IP and port.

```bash
./gradlew runClient --args="192.168.56.5 59001"
```


I launched two chat windows on the host machine to verify client-server communication. Messages were exchanged successfully. 
To document this, I took a screenshot showing the active connection and message flow.

[![image.png](https://i.postimg.cc/DwRqzg9S/image.png)](https://postimg.cc/G4JB7khR)

## Execute the gradle_basic_demo project - ca1 part3

In this part of the assignment, I focused on building and executing another component of the **gradle_basic_demo
project** using the virtual machine.

1. I navigated to the `react-and-spring-data-rest-basic` directory.
2. I ran the following commands to build the application and start the Spring Boot server, making it accessible via the web.

```bash
./gradlew build
./gradlew bootRun
```
[![image.png](https://i.postimg.cc/7YYf7W05/image.png)](https://postimg.cc/BtkSfNh3)
[![image.png](https://i.postimg.cc/2565LbZg/image.png)](https://postimg.cc/4YDGr3w1)


3. With the server running, I opened a browser and entered the following URL to access the Spring Boot app’s landing page. 
4. This confirmed the VM-hosted server was active and able to handle network requests.

```bash
http://192.168.56.5:8080/
```
[![image.png](https://i.postimg.cc/vHYDwNmQ/image.png)](https://postimg.cc/vDKGWX9j)
## Conclusion

This report outlines the setup and execution of a virtual environment using VirtualBox for **Class Assignment 2 Part 1**. 
The tasks included creating a virtual machine, configuring its network and services, and deploying essential development tools for software projects.
The virtualization process provided valuable insights into configuring and managing virtual machines in a DevOps context. 
Successfully running the Spring Boot tutorial and gradle_basic_demo projects within this environment showcased the ability to simulate real-world software deployment and operations.
Key takeaways include a deeper understanding of network configuration in virtualized environments and the complexities of software setup on virtual platforms. 
Challenges such as network interface configuration and ensuring smooth communication between the host and guest machines were addressed, 
enhancing my understanding of virtualization technologies.
Overall, this assignment has been instrumental in building skills for managing complex environments and will aid my ongoing educational and professional growth in DevOps.

[Back to top](#ca2-part1-virtualization-with-vagrant-technical-report)