# CA2 Part1: Virtualization with Vagrant: Technical Report

**Author:** João Cardoso

**Date:** 04/04/2025

**Discipline:** DevOps

**Program:** SWitCH DEV

**Institution:** Instituto Superior de Engenharia/ Instituto Politécnico do Porto

- Part 1 Contents:
    - [Part1 :Introduction](#part1-introduction)
    - [Create a Virtual Machine](#create-a-virtual-machine)
    - [Configure Network and Services](#configure-network-and-services)
    - [Clone the Repository](#clone-the-repository)
    - [Set Up Development Environment](#set-up-development-environment)
    - [Run the Spring Boot Tutorial Basic Project – CA1 Part 1](#run-the-spring-boot-tutorial-basic-project---ca1-part-1)
    - [Execute the gradle_basic_demo Project – CA1 Part 2](#execute-the-gradle_basic_demo-project---ca1-part-2)
    - [Execute the gradle_basic_demo Project – CA1 Part 3](#execute-the-gradle_basic_demo-project---ca1-part-3)
    - [Conclusion Part1](#conclusion-part1)

---
- Part 2 Contents:
  - [Part2 Introduction](#part2-introduction)
  - [Environment Setup](#environment-setup)
  - [Setup Base Project](#setup-base-project)
  - [Vagrantfile](#vagrantfile)
  - [Connecting Spring Boot to H2 Database](#connecting-spring-boot-to-h2-database)
  - [Running the Project](#running-the-project)
  - [Vagrant commands](#vagrant-commands)
  - [Alternative Solution](#alternative-solution)
  - [Conclusion Part1](#conclusion-part1)

## Part1 Introduction

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
## Conclusion Part1

This report outlines the setup and execution of a virtual environment using VirtualBox for **Class Assignment 2 Part 1**. 
The tasks included creating a virtual machine, configuring its network and services, and deploying essential development tools for software projects.
The virtualization process provided valuable insights into configuring and managing virtual machines in a DevOps context. 
Successfully running the Spring Boot tutorial and gradle_basic_demo projects within this environment showcased the ability to simulate real-world software deployment and operations.
Key takeaways include a deeper understanding of network configuration in virtualized environments and the complexities of software setup on virtual platforms. 
Challenges such as network interface configuration and ensuring smooth communication between the host and guest machines were addressed, 
enhancing my understanding of virtualization technologies.
Overall, this assignment has been instrumental in building skills for managing complex environments and will aid my ongoing educational and professional growth in DevOps.


## Part2 Introduction

This report summarizes **Class Assignment 2 - Part 2**, focusing on virtualization using Vagrant. 
The task involved setting up a virtualized environment to deploy a Spring Boot application connected to an H2 database. 
Below, I outline the steps for configuring Vagrant, linking the Spring Boot app to the H2 database, and successfully running the project. 
Additionally, I compare VMware and VirtualBox as alternative virtualization solutions.

## Environment Setup

I used these main steps to set up a Vagrant virtual environment:

**Download Vagrant**:
I downloaded the right version for my system from the official Vagrant website.

**Install Vagrant**:
I ran the installer from the Vagrant website and followed the on-screen prompts — the setup was simple.

**Verify Installation**:
I checked the Vagrant installation by running:

   ```bash
   vagrant --version
   ```

This command displayed the installed version of Vagrant, which confirmed that the installation was successful.

**Update .gitignore**: To maintain a clean repository, I excluded Vagrant files and build artifacts by adding these lines to .gitignore:

```
.vagrant/
*.war
```

## Setup Base Project

**Clone the Base Project**:
I cloned the base Vagrant project to get the required configuration files by running the following command:

```bash
git clone https://bitbucket.org/pssmatos/vagrant-multi-spring-tut-demo/
```

This step cloned the repository that contains the initial setup and configurations needed for our project.

**Copy the Vagrantfile**:
After cloning the base project, I copied the Vagrantfile from the cloned repository to my project's specific directory.
Here's how I did it:

```bash
cp -r vagrant-multi-spring-tut-demo/Vagrantfile C/Users/João/Desktop/switch/03-DevOps/devops-24-25-1241911/ca2/part2
```

This confirmed that my project directory includes the initial Vagrant configuration to proceed with the setup.

## Vagrantfile

The Vagrantfile is the configuration file that defines the virtual machine (VM) settings and provisions. 
After setting up the initial Vagrantfile, I made the following key modifications to tailor it to our project requirements:

1. **Changed the Repository URL:** Updated the repository URL in the Vagrantfile to point to my specific project.

2. **Changed the Path:** Modified the path in the Vagrantfile to point to the correct directory.

3. **Added bootRun Command:** Added the ./gradlew bootRun command to run the Spring Boot application.

4. **Updated the Java Version:** Changed the Java version in the setup to OpenJDK 17.

Here's the updated Vagrantfile:

```ruby
# See: https://manski.net/2016/09/vagrant-multi-machine-tutorial/
# for information about machine names on private network
Vagrant.configure("2") do |config|
  config.ssh.forward_agent = true
  config.vm.box = "ubuntu/bionic64"

  # This provision is common for both VMs
  config.vm.provision "shell", inline: <<-SHELL
    sudo apt-get update -y
    sudo apt-get install -y iputils-ping avahi-daemon libnss-mdns unzip \
        openjdk-17-jdk-headless
    # ifconfig
  SHELL

  #============
  # Configurations specific to the database VM
  config.vm.define "db" do |db|
    db.vm.box = "ubuntu/bionic64"
    db.vm.hostname = "db"
    db.vm.network "private_network", ip: "192.168.33.11"

    # We want to access H2 console from the host using port 8082
    # We want to connet to the H2 server using port 9092
    db.vm.network "forwarded_port", guest: 8082, host: 8082
    db.vm.network "forwarded_port", guest: 9092, host: 9092

    # We need to download H2
    db.vm.provision "shell", inline: <<-SHELL
      wget https://repo1.maven.org/maven2/com/h2database/h2/1.4.200/h2-1.4.200.jar
    SHELL

    # The following provision shell will run ALWAYS so that we can execute the H2 server process
    # This could be done in a different way, for instance, setiing H2 as as service, like in the following link:
    # How to setup java as a service in ubuntu: http://www.jcgonzalez.com/ubuntu-16-java-service-wrapper-example
    #
    # To connect to H2 use: jdbc:h2:tcp://192.168.33.11:9092/./jpadb
    db.vm.provision "shell", :run => 'always', inline: <<-SHELL
      java -cp ./h2*.jar org.h2.tools.Server -web -webAllowOthers -tcp -tcpAllowOthers -ifNotExists > ~/out.txt &
    SHELL
  end

    # Configurations specific to the webserver VM
      config.vm.define "web" do |web|
        web.vm.hostname = "web"
        web.vm.network "private_network", ip: "192.168.56.10"
        web.vm.network "forwarded_port", guest: 8080, host: 8080
        # Sync the host's SSH key folder into the VM (read-only)
        web.vm.synced_folder "~/.ssh", "/home/vagrant/.ssh_host", type: "virtualbox"

     # We set more ram memmory for this VM
        web.vm.provider "virtualbox" do |v|
          v.memory = 1024
        end

        # Provisioning script for the web VM
        web.vm.provision "shell", privileged: false, inline: <<-SHELL
          # Copy the SSH private key and set correct permissions
          cp /home/vagrant/.ssh_host/id_ed25519 ~/.ssh/id_ed25519
          chmod 600 ~/.ssh/id_ed25519

          # Trust GitHub host to avoid SSH warnings
          ssh-keyscan github.com >> ~/.ssh/known_hosts

          # Clone the repository only if it doesn't exist
          if [ ! -d "devops-24-25-1241911" ]; then
            git clone git@github.com:JoaoRuaCardoso/devops-24-25-1241911.git
          fi

          cd devops-24-25-1241911/ca1/part3/react-and-spring-data-rest-basic
          chmod u+x gradlew
          ./gradlew clean build
          ./gradlew bootRun

          # Deploy the WAR to Tomcat
          sudo cp ./build/libs/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT.jar /var/lib/tomcat9/webapps
        SHELL
 end
end

```

## Connecting Spring Boot to H2 Database

To connect the Spring Boot application to the H2 database, I made the following changes to the react-and-spring-data-rest-basic project:

**Modify application.properties**:
I added the necessary properties to `src/main/resources/application.properties` to connect to the H2 database:

```properties
spring.data.rest.base-path=/api
server.servlet.context-path=/basic-0.0.1-SNAPSHOT
spring.datasource.url=jdbc:h2:tcp://192.168.33.11:9092/./jpadb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.h2.console.settings.web-allow-others=true
spring.jpa.hibernate.ddl-auto=create
```

**Update React App.js**:
The `src/App.js` needed adjustments to match the new backend path:

```javascript
client({method: 'GET', path: '/basic-0.0.1-SNAPSHOT/api/employees'}).done(response => {
```

## Running the Project

Before running the project, I ensured that Virtual Box was initialized and that the repository to be cloned was public.
Then, I navigated to the project directory and executed the following command:

```bash
vagrant up
```

This command started the VMs and provisioned them based on the Vagrantfile configurations.

Once the VMs were up and running, I navigated to http://localhost:8080/basic-0.0.1-SNAPSHOT/ in my web browser to verify if the 
Spring Boot application was running correctly. Below is a screenshot of the result:

[![image.png](https://i.postimg.cc/XJN8HTSN/image.png)](https://postimg.cc/GBNG9gm6)

I also accessed the H2 console by visiting http://localhost:8082/h2-console and connected to the H2 database using the
jdbc:h2:tcp://192.168.33.11:9092/./jpadb
Below is a snapshot of the H2 Login page, where I entered the connection details:

[![image.png](https://i.postimg.cc/wBWH3Rz6/image.png)](https://postimg.cc/JyBS2ntg)

After connecting to the H2 database, I was able to view the tables and data stored in the database. 
This setup allowed me to run the Spring Boot application and interact with the H2 database seamlessly. 
Below is a screenshot of the H2 console showing the EMPLOYEE database table:

[![image.png](https://i.postimg.cc/T36WJFDK/image.png)](https://postimg.cc/ZCwRTfjm)

These steps confirmed that the Spring Boot application was working as expected and could successfully communicate with the H2 database.

## Vagrant commands

Here are the main Vagrant commands I used during the setup and troubleshooting:

| Command             | Description                                                                             |
|---------------------|-----------------------------------------------------------------------------------------|
| `vagrant init`      | Initializes a new Vagrant environment by creating a Vagrantfile.                        |
| `vagrant up`        | Starts and provisions the Vagrant environment as defined in the Vagrantfile.            |
| `vagrant halt`      | Stops the Vagrant machine, effectively powering it down.                                |
| `vagrant reload`    | Restarts the Vagrant machine, reloading the Vagrantfile if it has changed.              |
| `vagrant destroy`   | Stops and destroys all resources that were created during the machine creation process. |
| `vagrant ssh`       | Connects to the machine via SSH.                                                        |
| `vagrant status`    | Shows the current status of the Vagrant machine.                                        |
| `vagrant suspend`   | Suspends the machine, saving its current running state.                                 |
| `vagrant resume`    | Resumes a suspended Vagrant machine.                                                    |
| `vagrant provision` | Provisions the Vagrant machine based on the configuration specified in the Vagrantfile. |

These commands helped manage and navigate the virtualized environments efficiently.

## Alternative Solution

In this section, I explore VMware as an alternative virtualization tool to VirtualBox. 
Below is a detailed comparison between VMware and VirtualBox, followed by instructions on how VMware can be used with Vagrant 
to achieve the goals outlined for this assignment.

**Comparison of VMware and VirtualBox**

- **VirtualBox:**
    - **Overview**: A free, open-source hypervisor from Oracle, favored for its ease of use and support for various
      OSes.
    - **Pros**:
        - Free and open-source.
        - User-friendly GUI.
        - Supports many guest operating systems.
    - **Cons**:
        - Limited advanced features.
        - Can be slower with 3D graphics and larger VMs.

- **VMware (Workstation and Fusion):**
    - **Overview**: A professional-grade solution from VMware, known for performance and advanced features.
    - **Pros**:
        - High performance and stability.
        - Advanced features like snapshots, cloning, and shared VMs.
        - Integrates well with other VMware enterprise products.
    - **Cons**:
        - Expensive, requiring a license post-trial.
        - Steeper learning curve for enterprise-grade features.

**Using VMware with Vagrant**

Integrating VMware with Vagrant involves a few steps:

1. **Install the Vagrant VMware Utility**. This is necessary for Vagrant to manage VMware VMs.

```bash
# Example for installing on Linux
wget https://releases.hashicorp.com/vagrant-VMware-utility/1.0.14/vagrant-VMware-utility_1.0.14_x86_64.deb
sudo dpkg -i vagrant-VMware-utility_1.0.14_x86_64.deb
```

2. **Install the Vagrant Plugin for VMware**. This plugin allows Vagrant to interact with VMware.

```bash
vagrant plugin install vagrant-VMware-desktop
```

3. **Configure the Vagrantfile**. Update your Vagrantfile to use VMware as the provider.

```ruby
Vagrant.configure("2") do |config|
  config.vm.box = "hashicorp/bionic64"
  config.vm.provider "VMware_desktop" do |v|
    v.vmx["memsize"] = "1024"
    v.vmx["numvcpus"] = "2"
  end
end
```

Switching to VMware with Vagrant offers a robust solution that enhances the virtualization capabilities of our development environment.
It supports advanced features and delivers better performance, which can be especially beneficial for complex and larger projects.

This alternative aligns with the goal of improving our virtualization setup, ultimately enhancing the development experience 
and facilitating smoother transitions to production-like environments.

## Conclusion

This technical report documents the setup and execution of **Class Assignment 2 - Part 2**, focusing on virtualization with Vagrant. 
By configuring the Vagrant environment, connecting the Spring Boot application to the H2 database, and running the project successfully,
I have demonstrated the practical application of virtualization concepts in a real-world scenario. 
Additionally, the alternative solution using VMware with Vagrant has been explored, emphasizing the differences between VMware and 
VirtualBox and showcasing the benefits of using VMware for advanced virtualization needs.

[Back to top](#ca2-part1-virtualization-with-vagrant-technical-report)