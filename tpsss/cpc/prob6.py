n = int(input())
s = input().strip()

count = 0
for i in range(n):
    if s[i] in '2468':
        count += i + 1

print(count)
