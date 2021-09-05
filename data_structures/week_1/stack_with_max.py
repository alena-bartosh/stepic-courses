# task 1.2.4 from https://stepik.org/course/1547/syllabus
from enum import Enum
from typing import Optional


class StackWithMaximum:
    def __init__(self) -> None:
        self.storage = []
        self.__max_values = []

    def push(self, value: int) -> None:
        self.storage.append(value)

        if len(self.__max_values) == 0:
            maximum_in_stack = value
        else:
            maximum_in_stack = self.__max_values[-1]

        self.__max_values.append(max(value, maximum_in_stack))

    def pop(self) -> int:
        self.__max_values.pop()
        return self.storage.pop()

    def max(self) -> Optional[int]:
        return None if len(self.__max_values) == 0 else self.__max_values[-1]


class StackCommandExecutor:
    class Command(Enum):
        PUSH = 'push'
        POP = 'pop'
        MAX = 'max'

    def __init__(self) -> None:
        self.stack = StackWithMaximum()

    def execute(self, command: str) -> None:
        if command == self.Command.MAX.value:
            print(self.stack.max())
            return

        if command == self.Command.POP.value:
            self.stack.pop()
            return

        push_command = self.Command.PUSH.value
        if command.startswith(push_command):
            value = int(command.split(f'{push_command} ')[1])
            self.stack.push(value)
            return

        raise Exception('Unknown command!')


def main() -> None:
    commands_count = int(input())
    executor = StackCommandExecutor()

    for i in range(commands_count):
        executor.execute(input())


if __name__ == '__main__':
    main()
