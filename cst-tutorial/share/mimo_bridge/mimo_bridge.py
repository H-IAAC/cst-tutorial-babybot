import socket
import json
import gymnasium as gym
import numpy as np

# Inicializa o ambiente com visualização
env = gym.make("Reacher-v4", render_mode="human")
obs, _ = env.reset()

# Abre socket para comunicação com Java
server = socket.socket()
server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
server.bind(("0.0.0.0", 5000))
server.listen(1)
print("Python bridge: aguardando conexão na porta 5000...")
conn, _ = server.accept()
print("Conexão recebida!")

# Loop de interação
while True:
    data = conn.recv(1024).decode()
    if not data:
        break
    try:
        # Lê ação vinda do Java
        action = json.loads(data)
        obs, _, terminated, truncated, _ = env.step(action)

        # Tratar obs diretamente como array
        if isinstance(obs, np.ndarray):
            obs_clean = obs.tolist()
        else:
            obs_clean = obs  # Caso seja já serializável

        conn.sendall((json.dumps(obs_clean) + "\n").encode())

    except Exception as e:
        conn.sendall((json.dumps({"error": str(e)}) + "\n").encode())



        # Envia observação para o Java
        conn.sendall((json.dumps(obs_clean) + "\n").encode())

    except Exception as e:
        error_msg = {"error": str(e)}
        conn.sendall((json.dumps(error_msg) + "\n").encode())

