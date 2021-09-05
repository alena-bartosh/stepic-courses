# task 1.2.1 from https://stepik.org/course/1547/syllabus
from collections import deque


def is_correct_brackets(cur: str, prev: str) -> bool:
    cur_prev_correct_brackets = {
        ']': '[',
        '}': '{',
        ')': '(',
    }

    return cur_prev_correct_brackets[cur] == prev


def check(text: str) -> int:
    stack = deque()

    for ind, symbol in enumerate(text, start=1):
        if symbol not in ['[', ']', '{', '}', '(', ')']:
            continue

        if symbol in ['[', '(', '{']:
            stack.append((symbol, ind))
            continue

        # NOTE: here and below symbol is closed bracket
        if len(stack) == 0:
            return ind

        prev_bracket, prev_ind = stack.pop()

        if not is_correct_brackets(symbol, prev_bracket):
            return ind

    # Return index of the last element in stack if it is not empty
    return 0 if not stack else stack.pop()[1]


def main() -> None:
    text = input()

    result = check(text)

    if result == 0:
        print('Success')
    else:
        print(result)


if __name__ == '__main__':
    # assert check("()[]}") == 5
    # assert check("([](){([])})") == 0
    # assert check("{{[()]]") == 7
    # assert check("{{{[][][]") == 3
    # assert check("{*{{}") == 3
    # assert check("[[*") == 2
    # assert check("{*}") == 0
    # assert check("{{") == 2
    # assert check("{}") == 0
    # assert check("") == 0
    # assert check("}") == 1
    # assert check("*{}") == 0
    # assert check("{{{**[][][]") == 3

    main()
