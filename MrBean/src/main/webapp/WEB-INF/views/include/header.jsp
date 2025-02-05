<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <title><c:out value="${pageTitle} - MrBean" default="MrBean"/></title>
  <meta content="MrBean Web Application" name="description">
<meta content="MrBean, Web, Management" name="keywords">

  <!-- Toast CSS -->
  <link rel="stylesheet" href="<c:url value='/resources/css/toastStyle.css'/>">

  <!-- Costom CSS -->
  <link rel="stylesheet" href="<c:url value='/resources/css/bomStyle.css'/>">

  <!-- Favicons -->
  <link href="${pageContext.request.contextPath}/resources/assets/img/favicon.png" rel="icon">
  <link href="${pageContext.request.contextPath}/resources/assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
  
  <!-- jQuery를 먼저 로드 -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

  <!-- Vendor CSS Files -->
  <link href="${pageContext.request.contextPath}/resources/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/assets/vendor/quill/quill.snow.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/assets/vendor/quill/quill.bubble.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/assets/vendor/simple-datatables/style.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="${pageContext.request.contextPath}/resources/assets/css/style.css" rel="stylesheet">

  <!-- jQuery CDN -->
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

  <!-- Vendor JS Files -->
  <script src="${pageContext.request.contextPath}/resources/assets/vendor/apexcharts/apexcharts.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/assets/vendor/chart.js/chart.umd.js"></script>
  <script src="${pageContext.request.contextPath}/resources/assets/vendor/echarts/echarts.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/assets/vendor/simple-datatables/simple-datatables.js"></script>
  <script src="${pageContext.request.contextPath}/resources/assets/vendor/tinymce/tinymce.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/assets/vendor/php-email-form/validate.js"></script>

  <!-- Template Main JS File -->
  <script src="${pageContext.request.contextPath}/resources/assets/js/main.js"></script>
  <!-- Navigation JS File -->
  <script src="${pageContext.request.contextPath}/resources/assets/js/navigation.js"></script>
</head>
<body>  
  <!-- ======= Header ======= -->
<header id="header" class="header fixed-top d-flex align-items-center">
    <div class="d-flex align-items-center justify-content-between">
        <a href="${pageContext.request.contextPath}/user/main" class="logo d-flex align-items-center">
            <img src="${pageContext.request.contextPath}/resources/assets/img/coffee.png" alt="" style="width: 35px; height: auto;">
            <span class="d-none d-lg-block">Mr.BEAN</span>
        </a>
        <i class="bi bi-list toggle-sidebar-btn"></i>
    </div>

    <div class="search-bar">
        <form class="search-form d-flex align-items-center" method="POST" action="#">
            <input type="text" name="query" placeholder="Search" title="Enter search keyword">
            <button type="submit" title="Search"><i class="bi bi-search"></i></button>
        </form>
    </div>

    <nav class="header-nav ms-auto">
        <ul class="d-flex align-items-center">
            <li class="nav-item dropdown pe-3" style="margin-right: 50px;"> <!-- 추가 여백 -->
                <a class="nav-link nav-profile d-flex align-items-center" href="#" data-bs-toggle="dropdown">
                    <!-- 사용자 프로필 이미지 -->
                    <c:choose>
                        <c:when test="${sessionScope.loggedInUser.URoleenum == 'ADMIN'}">
                            <img src="${pageContext.request.contextPath}/resources/assets/img/admin.png" alt="Admin"
                                 class="rounded-circle" style="width: 80px; height: 80px; margin-right: 15px;">
                        </c:when>
                        <c:when test="${sessionScope.loggedInUser.URoleenum == 'MANAGER'}">
                            <img src="${pageContext.request.contextPath}/resources/assets/img/manager.png" alt="Manager"
                                 class="rounded-circle" style="width: 80px; height: 80px; margin-right: 15px;">
                        </c:when>
                        <c:when test="${sessionScope.loggedInUser.URoleenum == 'MEMBER'}">
                            <img src="${pageContext.request.contextPath}/resources/assets/img/member.png" alt="Member"
                                 class="rounded-circle" style="width: 80px; height: 80px; margin-right: 15px;">
                        </c:when>

                        
                        <c:otherwise>
    						<img src="${pageContext.request.contextPath}/resources/assets/img/young.png" alt="Default"
        						 class="rounded-circle" style="width: 40px; height: 40px; margin-right: 15px;">
						</c:otherwise>
                        
                   		 </c:choose>

          <!-- 사용자 이름 -->
    <span class="d-block" style="font-size: 20px; font-weight: bold;">
        <c:choose>
            <c:when test="${not empty sessionScope.loggedInUser}">
                ${sessionScope.loggedInUser.UUsername}
            </c:when>
            <c:otherwise>
                	Guest
            </c:otherwise>
        </c:choose>
    </span>
 


                </a>
   <!-- 드롭다운 메뉴 -->
    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
        <li class="dropdown-header text-center">
            <h6>${sessionScope.loggedInUser.UUsername}</h6>
            <span>${sessionScope.loggedInUser.URoleenum}</span>
        </li>
        <li>
            <hr class="dropdown-divider">
        </li>
        <li>
            <a class="dropdown-item d-flex align-items-center" href="${pageContext.request.contextPath}/user/info">
                <i class="bi bi-person"></i>
                <span>내 정보</span>
            </a>
        </li>
        <li>
            <a class="dropdown-item d-flex align-items-center" href="${pageContext.request.contextPath}/user/changePassword">
                <i class="bi bi-key"></i>
                <span>비밀번호 변경</span>
            </a>
        </li>
        <li>
            <hr class="dropdown-divider">
        </li>
        <li>
            <a class="dropdown-item d-flex align-items-center" href="${pageContext.request.contextPath}/user/logout">
                <i class="bi bi-box-arrow-right"></i>
                <span>로그아웃</span>
            </a>
       	 </li>
   		 </ul>
		</li>
	 </ul>
   </nav>
</header>


  <!-- ======= Sidebar ======= -->
  <!-- "PAGES" 스타일 섹션 -->
<li class="nav-heading">PAGES</li>

  
  <aside id="sidebar" class="sidebar">
    <ul class="sidebar-nav" id="sidebar-nav">
      <!-- "PAGES" 스타일 섹션 -->
<li class="nav-heading">Mr.bean Process</li>

      <li class="nav-item">
        <a class="nav-link collapsed" data-bs-target="#components-nav" data-bs-toggle="collapse" href="#">
          <i class="bi bi-menu-button-wide"></i><span>기준정보관리</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="components-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
          <li>
            <a href="/rawMaterials/list">
              <i class="bi bi-circle"></i><span>원자재</span>
            </a>
          </li>
          <li>
            <a href="/warehouses/create">
              <i class="bi bi-circle"></i><span>창고</span>
            </a>
          </li>
          <li>
            <a href="/billofmaterials">
              <i class="bi bi-circle"></i><span>자재 명세서</span>
            </a>
          </li>
          <li>
            <a href="/products/list">
              <i class="bi bi-circle"></i><span>완제품</span>
            </a>
          </li>
        </ul>
      </li><!-- End Components Nav -->

<li class="nav-item">
    <a class="nav-link collapsed" data-bs-target="#forms-nav" data-bs-toggle="collapse" href="#">
        <i class="bi bi-journal-text"></i><span>생산관리</span><i class="bi bi-chevron-down ms-auto"></i>
    </a>
    <ul id="forms-nav" class="nav-content collapse" data-bs-parent="#sidebar-nav">
        <li>
            <a href="/productionplan/plan">
                <i class="bi bi-circle"></i><span>생산계획 관리</span>
            </a>
        </li>
        <li>
            <a href="/workorders/work">
                <i class="bi bi-circle"></i><span>작업지시 관리</span>
            </a>
        </li>
    </ul>
</li>

      <li class="nav-item">
        <a class="nav-link collapsed" data-bs-target="#tables-nav" data-bs-toggle="collapse" href="#">
          <i class="bi bi-layout-text-window-reverse"></i><span>품질관리</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="tables-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
          <li>
            <a href="/rmqcontrol/main">
              <i class="bi bi-circle"></i><span>원자재 검사 관리</span>
            </a>
          </li>
          <li>
            <a href="/fpcontrol/main">
              <i class="bi bi-circle"></i><span>완제품 검사 관리</span>
            </a>
          </li>
        </ul>
      </li><!-- End Tables Nav -->

      <li class="nav-item">
        <a class="nav-link collapsed" data-bs-target="#charts-nav" data-bs-toggle="collapse" href="#">
          <i class="bi bi-bar-chart"></i><span>재고관리</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="charts-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
          <li>
            <a href="/stock/list">
              <i class="bi bi-circle"></i><span>원자재 재고 목록</span>
            </a>
          </li>
          <li>
            <a href="/stockP/splist">
              <i class="bi bi-circle"></i><span>완제품 재고 목록</span>
            </a>
          </li>
        </ul>
      </li><!-- End Charts Nav -->

      <li class="nav-item">
        <a class="nav-link collapsed" data-bs-target="#icons-nav" data-bs-toggle="collapse" href="#">
          <i class="bi bi-gem"></i><span>창고관리</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="icons-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
          <li>
            <a href="icons-bootstrap.html">
              <i class="bi bi-circle"></i><span>Bootstrap Icons</span>
            </a>
          </li>
          <li>
            <a href="icons-remix.html">
              <i class="bi bi-circle"></i><span>Remix Icons</span>
            </a>
          </li>
          <li>
            <a href="icons-boxicons.html">
              <i class="bi bi-circle"></i><span>Boxicons</span>
            </a>
          </li>
        </ul>
      </li><!-- End Icons Nav -->

<!-- "PAGES" 스타일 섹션 -->
<li class="nav-heading">PAGES</li>


  <!-- 로그인 상태에 따라 표시되는 메뉴 -->
    <li class="nav-item">
      <c:choose>
        <c:when test="${not empty sessionScope.loggedInUser}">
          <a class="nav-link collapsed" href="${pageContext.request.contextPath}/user/info">
            <i class="bi bi-person"></i>
            <span>내정보보기</span>
          </a>
        </c:when>
      </c:choose>
    </li>

    <li class="nav-item">
      <c:choose>
        <c:when test="${not empty sessionScope.loggedInUser}">
          <a class="nav-link collapsed" href="${pageContext.request.contextPath}/user/passwordcheck">
            <i class="bi bi-question-circle"></i>
            <span>내정보변경</span>
          </a>
        </c:when>
      </c:choose>
    </li>

    <!-- 회원가입 메뉴: 로그인 전 상태에서만 표시 -->
    <li class="nav-item">
      <c:choose>
        <c:when test="${empty sessionScope.loggedInUser}">
          <a class="nav-link collapsed" href="${pageContext.request.contextPath}/user/register">
            <i class="bi bi-card-list"></i>
            <span>회원가입</span>
          </a>
        </c:when>
      </c:choose>
    </li>

    <!-- 로그인 버튼: 로그인 상태가 아닐 때만 표시 -->
    <li class="nav-item">
      <c:choose>
        <c:when test="${empty sessionScope.loggedInUser}">
          <a class="nav-link collapsed" href="${pageContext.request.contextPath}/user/login">
            <i class="bi bi-box-arrow-in-right"></i>
            <span>로그인</span>
          </a>
        </c:when>
      </c:choose>
    </li>

	<!-- 비밀번호 변경 메뉴: 로그인 상태에서만 표시 -->
	<c:if test="${not empty sessionScope.loggedInUser}">
    	<li class="nav-item">
        	<a class="nav-link collapsed" href="${pageContext.request.contextPath}/user/changePassword">
           	 <i class="bi bi-key"></i>
            <span>비밀번호 변경</span>
        </a>
    </li>
</c:if>

  <!-- 로그인 상태에서만 보이는 섹션 -->
        <c:if test="${not empty sessionScope.loggedInUser}">
            <li class="nav-item">
                <a class="nav-link collapsed" href="${pageContext.request.contextPath}/user/list">
                    <i class="bi bi-person-lines-fill"></i>
                    <span>회원 목록</span>
                </a>
            </li>
        </c:if>



    <!-- 로그아웃 버튼: 로그인 상태일 때만 표시 -->
    <li class="nav-item">
      <c:choose>
        <c:when test="${not empty sessionScope.loggedInUser}">
          <a class="nav-link collapsed" href="${pageContext.request.contextPath}/user/logout">
            <i class="bi bi-box-arrow-right"></i>
            <span>로그아웃</span>
          </a>
        </c:when>
      </c:choose>
    </li>


    
    
  </ul>
</aside>
  <main id="main" class="main">

    <div class="pagetitle">
  <h1><c:out value="${pageTitle}" default="Mr.BEAN"/></h1>
  <nav>
    <ol class="breadcrumb horizontal-links">
      <li class="breadcrumb-item">
        <a href="${pageContext.request.contextPath}/user/mcount">
          <i class="bi bi-house-door"></i> Member ratio
        </a>
      </li>
      <li class="breadcrumb-item">
        <a href="${pageContext.request.contextPath}/user/process">
          <i class="bi bi-house-door"></i> process
        </a>
      </li>
      <c:forEach var="crumb" items="${breadcrumbList}">
        <c:choose>
          <c:when test="${crumb.active}">
            <li class="breadcrumb-item active">${crumb.label}</li>
          </c:when>
          <c:otherwise>
            <li class="breadcrumb-item"><a href="${crumb.link}">${crumb.label}</a></li>
          </c:otherwise>
        </c:choose>
      </c:forEach>
    </ol>
  </nav>
</div>

<style>
  .horizontal-links {
    display: flex;
    flex-direction: row;
    gap: 20px; /* 링크 간 간격 조정 */
    padding: 0;
    list-style: none;
  }

  .horizontal-links .breadcrumb-item {
    margin-bottom: 0; /* 세로 간격 제거 */
  }

  .horizontal-links .breadcrumb-item a {
    text-decoration: none;
    color: #6f4e37; /* 커피색 (Hex 코드) */
    font-weight: bold; /* 강조를 위해 굵게 설정 */
  }

  .horizontal-links .breadcrumb-item a:hover {
    text-decoration: underline;
    color: #8B5A2B; /* 호버 시 조금 더 진한 커피색 */
  }
</style>



