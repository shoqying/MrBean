<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>원자재 품질 검사 관리</title>
</head>
<body>

    <h1>원자재 품질 검사 관리 목록</h1>
    <table>
        <tr>
            <th>순번</th>
            <th>입고일</th>
            <th>LOT 번호</th>
            <th>원자재명</th>
            <th>검사일자</th>
            <th>품질 검사</th>
            <th>상태</th>
            <th>작업지시번호</th>
            <th>검사량 (g)</th>
            <th>삭제</th>
        </tr>
        <tr>
            <td>1</td>
            <td>2025-01-10</td>
            <td>LOT12345</td>
            <td>원자재 A</td>
            <td>2025-01-12</td>
            <td>
                <select>
                    <option value="대기중">대기중</option>
                    <option value="완료">완료</option>
                </select>
            </td>
            <td>
                <select>
                	<option value="대기중">대기중</option>
                    <option value="합격">합격</option>
                    <option value="불합격">불합격</option>
                </select>
            </td>
            <td>WO56789</td>
            <td>500</td>
            <td><button onclick="confirmDelete(this)">삭제</button></td>
        </tr>
        <tr>
            <td>2</td>
            <td>2025-01-11</td>
            <td>LOT67890</td>
            <td>원자재 B</td>
            <td>2025-01-13</td>
            <td>
                <select>
                    <option value="대기중">대기중</option>
                    <option value="완료">완료</option>
                </select>
            </td>
            <td>
                <select>
                	<option value="대기중">대기중</option>
                    <option value="합격">합격</option>
                    <option value="불합격">불합격</option>
                </select>
            </td>
            <td>WO12345</td>
            <td>300</td>
            <td><button onclick="confirmDelete(this)">삭제</button></td>
        </tr>
    </table>
    
	<script>
	// 삭제 확인 및 행 삭제 함수
        function confirmDelete(button) {
            if (confirm("삭제하시겠습니까?")) {
                alert("삭제되었습니다.");
            } else {
                alert("삭제가 취소되었습니다.");
            }
        }
	</script>

</body>
</html>