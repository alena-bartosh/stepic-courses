# task 3.2.3 from https://stepik.org/course/1547/syllabus
from typing import List


P = 1_000_000_007  # big prime number
X = 263  # some fixed base


def rabin_karp(pattern: str, text: str) -> List[int]:
    """
    Compare the given pattern against all positions in the given text using hashing.
    Return positions in text, starting from which pattern is included in the text
    """
    results = []

    pattern_hash = 0
    window_hash = 0

    for i in range(len(pattern)):
        pattern_hash = (pattern_hash * X + ord(pattern[i])) % P
        window_hash = (window_hash * X + ord(text[i])) % P

    pattern_hash %= P
    window_hash %= P

    h = (X ** (len(pattern) - 1)) % P
    windows_count = len(text) - len(pattern) + 1

    # print(f'[pattern_hash={pattern_hash}][h={h}]')

    for start_window_ind in range(windows_count):
        finish_window_ind = start_window_ind + len(pattern)
        window_value = text[start_window_ind:finish_window_ind]

        # print(f'[Window={window_value}][Hash={window_hash}]')

        if window_hash == pattern_hash and window_value == pattern:
            results.append(start_window_ind)

        if finish_window_ind == len(text):
            break

        previous_char_ord = ord(text[start_window_ind])
        next_char_ord = ord(text[finish_window_ind])

        window_hash -= previous_char_ord * h
        window_hash = (window_hash + P) % P  # to avoid negative values
        window_hash = (window_hash * X + next_char_ord) % P

    return results


def main() -> None:
    pattern = input()
    text = input()

    inds = rabin_karp(pattern, text)
    print(' '.join(map(str, inds)))


if __name__ == '__main__':
    main()
