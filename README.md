# RunPlayer-folia

A powerful administrative tool for Minecraft 1.21 servers that allows Operators to force players to execute commands without granting them actual permissions or OP status.

## Features
- **Ghost Execution:** Commands are run via a temporary wrapper. The player never actually receives the permission node or OP status.
- **Security Blacklist:** Prevent the command from being used to run sensitive commands (like `/op` or `/stop`).
- **Identity Retention:** Plugins that target the "executor" will still target the player, not the admin who ran the command.

## Commands
- `/runfor <player> <command>`: Executes a command as the specified player. (Requires OP)

## Configuration
You can block specific commands in the `config.yml`:
```yaml
blacklisted-commands:
  - "op"
  - "deop"
  - "stop"
  - "luckperms"