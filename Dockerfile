FROM debian:bullseye-slim

LABEL maintainer="leolellisr <l261900@dac.unicamp.br>"

ENV DEBIAN_FRONTEND=noninteractive

# Atualizar e instalar dependências
RUN apt-get update && \
    apt-get install -y \
        dbus-x11 \
        wget \
        unzip \
        xfce4 \
        xfce4-terminal \
        novnc \
        x11vnc \
        xdotool \
        git \
        procps \
        xvfb \
        keyboard-configuration \
        mousepad \
        python3-pip \
        lsb-release \
        ca-certificates \
        sudo \
        libx11-dev \
        libxext-dev \
        libxtst-dev \
        libxrandr-dev \
        libfreetype6-dev \
        libxrender-dev \
        libxt-dev \
        libxaw7 \
        libxmu6 \
        libxt6 \
        libsm6 \
        libice6 \
        libx11-xcb1 \
        apt-transport-https && \
    apt-get clean

# Instalar Java 17 (OpenJDK 17)
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk

# Verificar a versão do Java
RUN java -version

# Instalar Gradle 7.6
RUN wget https://services.gradle.org/distributions/gradle-7.6-bin.zip -P /tmp && \
    unzip /tmp/gradle-7.6-bin.zip -d /opt && \
    ln -s /opt/gradle-7.6/bin/gradle /usr/local/bin/gradle && \
    rm /tmp/gradle-7.6-bin.zip

# Verificar se o Gradle foi instalado corretamente
RUN gradle -v

# Instalar Maven
RUN apt-get update && \
    apt-get install -y maven

# Configurar layout de teclado sem interação
RUN echo "keyboard-configuration keyboard-configuration/layout select English (US)" | debconf-set-selections && \
    echo "keyboard-configuration keyboard-configuration/model select Generic" | debconf-set-selections && \
    echo "keyboard-configuration keyboard-configuration/variant select USA" | debconf-set-selections && \
    echo "keyboard-configuration keyboard-configuration/options select" | debconf-set-selections

# Baixar e instalar NetBeans 26
RUN wget https://www.apache.org/dyn/closer.lua/netbeans/netbeans/26/netbeans-26-bin.zip?action=download -O /tmp/netbeans.zip && \
    unzip /tmp/netbeans.zip -d /opt/ && \
    rm /tmp/netbeans.zip && \
    ln -s /opt/netbeans-26/bin/netbeans /usr/local/bin/netbeans && \
    echo "export PATH=\$PATH:/opt/netbeans-26/bin" >> /etc/profile.d/netbeans.sh

# Copia e instala o pacote CST-CLI
COPY cst-cli_*.deb /tmp/cst-cli.deb
RUN apt-get update && \
    apt-get install -y /tmp/cst-cli.deb

# Adiciona CST-CLI ao PATH
ENV PATH="/opt/cst-cli/bin:$PATH"

# Instalar noVNC e Websockify + clonar MIMo
RUN pip3 install websockify && \
    git clone https://github.com/novnc/noVNC.git /opt/noVNC && \
    ln -s /opt/noVNC/vnc_lite.html /opt/noVNC/index.html && \
    git clone https://github.com/trieschlab/MIMo /MIMo && \
    sed -i '/stable-baselines3/d' /MIMo/requirements.txt && \
    pip install -r /MIMo/requirements.txt && \
    pip install -e /MIMo

# Expor as portas do servidor
EXPOSE 8080 5900

# Configurar VNC
RUN mkdir -p /root/.vnc /sharevnc && \
    x11vnc -storepasswd 123456 /root/.vnc/passwd

# Baixar bibliotecas Java necessárias para MindViewer
RUN mkdir -p /sharevnc/1_MIMoCoreModelExample/lib && \
    cd /sharevnc/1_MIMoCoreModelExample/lib && \
    wget -O jfreechart-1.5.4.jar https://repo1.maven.org/maven2/org/jfree/jfreechart/1.5.4/jfreechart-1.5.4.jar && \
    wget -O jcommon-1.0.24.jar https://repo1.maven.org/maven2/org/jfree/jcommon/1.0.24/jcommon-1.0.24.jar

# Instalar Geany (editor de código leve com destaque de sintaxe e build)
RUN apt-get update && \
    apt-get install -y geany && \
    apt-get clean
    
# Instalar Miniconda
RUN wget https://repo.anaconda.com/miniconda/Miniconda3-py311_24.1.2-0-Linux-x86_64.sh -O /tmp/miniconda.sh && \
    bash /tmp/miniconda.sh -b -p /opt/conda && \
    rm /tmp/miniconda.sh && \
    /opt/conda/bin/conda clean -a -y

ENV PATH="/opt/conda/bin:$PATH"

# Criar ambiente conda para BabyBench
RUN conda create -y -n babybench python=3.12 && \
    echo "source activate babybench" >> /root/.bashrc

# Instalar BabyBench
RUN git clone https://github.com/babybench/BabyBench2025_Starter_Kit.git /babybench && \
    /bin/bash -c "source activate babybench && cd /babybench && pip install -r requirements.txt && pip install -e MIMo"


# Comando de inicialização
CMD ["sh", "-c", "Xvfb :0 -screen 0 1024x768x24 & \
    export DISPLAY=:0 && \
    xfce4-session & \
    x11vnc -forever -usepw -display :0 & \
    /opt/noVNC/utils/novnc_proxy --vnc localhost:5900 --listen 8080"]
