#!/bin/sh
# HackerRank식 자동 채점 흉내 — 각 러너를 샘플 입력으로 실행하고 기대출력과 비교한다.
#   사용: ./verify-day1.sh
# 풀이가 TODO 상태면 러너가 예외를 던지므로 FAIL로 표시된다.

set -e
cd "$(dirname "$0")"

# task명 / 입력파일 / 기대출력파일
CASES="
runAddTwoNumbers add-two-numbers
runReverseList   reverse-list
runDetectCycle   detect-cycle
runMaxProduct    max-product
runMergeLists    merge-two-lists
"

pass=0
fail=0

echo "$CASES" | while read -r task name; do
  [ -z "$task" ] && continue
  in="samples/day1/$name.in"
  exp="samples/day1/$name.out"

  actual=$(./gradlew -q "$task" < "$in" 2>/dev/null || echo "<RUNTIME ERROR: 미구현이거나 예외>")
  expected=$(cat "$exp")

  if [ "$actual" = "$expected" ]; then
    printf "✅ %-18s PASS\n" "$task"
    pass=$((pass + 1))
  else
    printf "❌ %-18s FAIL\n" "$task"
    printf "     입력:   %s\n" "$(tr '\n' '|' < "$in")"
    printf "     기대:   %s\n" "$expected"
    printf "     실제:   %s\n" "$actual"
    fail=$((fail + 1))
  fi
done

echo "----------------------------------------"
echo "통과 $pass · 실패 $fail"
