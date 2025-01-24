<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Dashboard - NiceAdmin Bootstrap Template</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="${pageContext.request.contextPath}/resources/assets/img/favicon.png" rel="icon">
  <link href="${pageContext.request.contextPath}/resources/assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

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
</head>

<body>
  <!-- ======= Header ======= -->
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
                            <img src="${pageContext.request.contextPath}/resources/assets/img/default-profile.png" alt="Default"
                                 class="rounded-circle" style="width: 80px; height: 80px; margin-right: 15px;">
                        </c:otherwise>
                    </c:choose>

                    <!-- 사용자 이름 -->
                    <span class="d-none d-md-block" style="font-size: 20px; font-weight: bold;">
                        ${sessionScope.loggedInUser.UUsername}
                    </span>
                </a>

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

      </ul>
    </nav>
  </header>

  <!-- ======= Sidebar ======= -->
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
            <a href="components-alerts.html">
              <i class="bi bi-circle"></i><span>Alerts</span>
            </a>
          </li>
          <li>
            <a href="components-accordion.html">
              <i class="bi bi-circle"></i><span>Accordion</span>
            </a>
          </li>
          <li>
            <a href="components-badges.html">
              <i class="bi bi-circle"></i><span>Badges</span>
            </a>
          </li>
          <li>
            <a href="components-breadcrumbs.html">
              <i class="bi bi-circle"></i><span>Breadcrumbs</span>
            </a>
          </li>
          <li>
            <a href="components-buttons.html">
              <i class="bi bi-circle"></i><span>Buttons</span>
            </a>
          </li>
          <li>
            <a href="components-cards.html">
              <i class="bi bi-circle"></i><span>Cards</span>
            </a>
          </li>
          <li>
            <a href="components-carousel.html">
              <i class="bi bi-circle"></i><span>Carousel</span>
            </a>
          </li>
          <li>
            <a href="components-list-group.html">
              <i class="bi bi-circle"></i><span>List group</span>
            </a>
          </li>
          <li>
            <a href="components-modal.html">
              <i class="bi bi-circle"></i><span>Modal</span>
            </a>
          </li>
          <li>
            <a href="components-tabs.html">
              <i class="bi bi-circle"></i><span>Tabs</span>
            </a>
          </li>
          <li>
            <a href="components-pagination.html">
              <i class="bi bi-circle"></i><span>Pagination</span>
            </a>
          </li>
          <li>
            <a href="components-progress.html">
              <i class="bi bi-circle"></i><span>Progress</span>
            </a>
          </li>
          <li>
            <a href="components-spinners.html">
              <i class="bi bi-circle"></i><span>Spinners</span>
            </a>
          </li>
          <li>
            <a href="components-tooltips.html">
              <i class="bi bi-circle"></i><span>Tooltips</span>
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
            <a href="forms-editors.html">
                <i class="bi bi-circle"></i><span>작업지시 관리</span>
            </a>
        </li>
        <li>
            <a href="forms-validation.html">
                <i class="bi bi-circle"></i><span>Form Validation</span>
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
            <a href="tables-general.html">
              <i class="bi bi-circle"></i><span>General Tables</span>
            </a>
          </li>
          <li>
            <a href="tables-data.html">
              <i class="bi bi-circle"></i><span>Data Tables</span>
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
            <a href="charts-chartjs.html">
              <i class="bi bi-circle"></i><span>Chart.js</span>
            </a>
          </li>
          <li>
            <a href="charts-apexcharts.html">
              <i class="bi bi-circle"></i><span>ApexCharts</span>
            </a>
          </li>
          <li>
            <a href="charts-echarts.html">
              <i class="bi bi-circle"></i><span>ECharts</span>
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
      <h1>Mr.BEAN</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="index.html">Home</a></li>
          <li class="breadcrumb-item">Forms</li>
          <li class="breadcrumb-item active">Layouts</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->
    


