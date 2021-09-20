# task 3.2.1 from https://stepik.org/course/1547/syllabus
from typing import List, Tuple, Optional


class HashTableWithOpenAddressing:
    def __init__(self, n: int) -> None:
        self.n = n
        self.__slot: List[Optional[Tuple[int, str]]] = [None] * n

    def find_slot(self, key: int) -> int:
        i = key % self.n
        # Search until we either find the key, or find an empty slot
        while self.__slot[i] is not None and self.__slot[i][0] != key:
            i = (i + 1) % self.n
        return i

    def try_find_key(self, key: int) -> Optional[str]:
        i = self.find_slot(key)

        if self.__slot[i] is not None:
            return self.__slot[i][1]

        return None

    def add(self, key: int, value: str) -> None:
        i = self.find_slot(key)
        self.__slot[i] = (key, value)

    def remove(self, key: int) -> None:
        i = self.find_slot(key)

        if self.__slot[i] is None:
            return

        is_k_lies_in_i_j = False
        j = i

        # NOTE: table rebuilding like here https://en.wikipedia.org/wiki/Open_addressing
        while True:

            if not is_k_lies_in_i_j:
                self.__slot[i] = None

            is_k_lies_in_i_j = False

            j = (j + 1) % self.n

            if self.__slot[j] is None:
                break

            k = self.__slot[j][0] % self.n
            # k can be in |i.k.j| or |j.i.k.| or |k.j.i.|

            if i <= j and i < k <= j:
                is_k_lies_in_i_j = True
                continue

            self.__slot[i] = self.__slot[j]
            i = j


class PhoneBook:
    def __init__(self, n: int) -> None:
        self.storage = HashTableWithOpenAddressing(n)

    def do(self, command: List[str]) -> None:
        command_word = command[0]
        key = int(command[1])

        if command_word == 'add':
            value = command[2]
            self.storage.add(key, value)

        if command_word == 'del':
            self.storage.remove(key)

        if command_word == 'find':
            value = self.storage.try_find_key(key)

            print(value or 'not found')


def main() -> None:
    commands_count = int(input())
    phone_book = PhoneBook(commands_count)

    for _ in range(commands_count):
        phone_book.do(input().split(' '))


if __name__ == '__main__':
    main()
