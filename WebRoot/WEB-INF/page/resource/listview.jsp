<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ page import="java.io.*" %>
<%@ page import="com.lsj.common.model.Resource" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>
    <meta charset="utf-8">
    <title>Bootstrap Admin</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="${serverRoot}/static/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${serverRoot}/static/lib/font-awesome/css/font-awesome.css">

    <script src="${serverRoot}/static/lib/jquery-1.11.1.min.js" type="text/javascript"></script>
	<script src="${serverRoot}/static/lib/jQuery-Knob/js/jquery.knob.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function() {
            $(".knob").knob();
        });
    </script>


    <link rel="stylesheet" type="text/css" href="${serverRoot}/static/stylesheets/theme.css">
    <link rel="stylesheet" type="text/css" href="${serverRoot}/static/stylesheets/premium.css">

</head>
<body class=" theme-blue">

    <!-- Demo page code -->

    <script type="text/javascript">
        $(function() {
            var match = document.cookie.match(new RegExp('color=([^;]+)'));
            if(match) var color = match[1];
            if(color) {
                $('body').removeClass(function (index, css) {
                    return (css.match (/\btheme-\S+/g) || []).join(' ')
                })
                $('body').addClass('theme-' + color);
            }

            $('[data-popover="true"]').popover({html: true});
            
        });
    </script>
    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .navbar-default .navbar-brand, .navbar-default .navbar-brand:hover { 
            color: #fff;
        }
    </style>

    <script type="text/javascript">
        $(function() {
            var uls = $('.sidebar-nav > ul > *').clone();
            uls.addClass('visible-xs');
            $('#main-menu').append(uls.clone());
        });
    </script>

    <div class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="" href="index.html"><span class="navbar-brand"><span class="fa fa-paper-plane"></span> Aircraft</span></a></div>

        <div class="navbar-collapse collapse" style="height: 1px;">
          <ul id="main-menu" class="nav navbar-nav navbar-right">
            <li class="dropdown hidden-xs">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <span class="glyphicon glyphicon-user padding-right-small" style="position:relative;top: 3px;"></span> Jack Smith
                    <i class="fa fa-caret-down"></i>
                </a>

              <ul class="dropdown-menu">
                <li><a href="./">My Account</a></li>
                <li class="divider"></li>
                <li class="dropdown-header">Admin Panel</li>
                <li><a href="./">Users</a></li>
                <li><a href="./">Security</a></li>
                <li><a tabindex="-1" href="./">Payments</a></li>
                <li class="divider"></li>
                <li><a tabindex="-1" href="sign-in.html">Logout</a></li>
              </ul>
            </li>
          </ul>

        </div>
      </div>
    </div>
    

    <div class="sidebar-nav">
    <ul>
    <li><a href="#" data-target=".dashboard-menu" class="nav-header" data-toggle="collapse"><i class="fa fa-fw fa-dashboard"></i> Dashboard<i class="fa fa-collapse"></i></a></li>
    <li><ul class="dashboard-menu nav nav-list collapse in">
            <li><a href="index.html"><span class="fa fa-caret-right"></span>菜单管理</a></li>
    </ul></li>
    </div>

    <div class="content">
        <div class="header">
            <h1 class="page-title">Dashboard</h1>
            <ul class="breadcrumb">
            	<li><a href="index.html">Home</a> </li>
            	<li class="active">Dashboard</li>
        	</ul>
        </div>
        
<!-- ************************************************************************************** -->      
        <div class="main-content">
	        <div class="btn-toolbar list-toolbar">
			    <button class="btn btn-primary"><i class="fa fa-plus"></i>增加资源</button>
			</div>
			<table class="table">
				  <thead>
				    <tr>
				      <th>#</th>
				      <th>父亲id</th>
				      <th>资源名</th>
				      <th>url</th>
				      <th>权限位</th>
				      <th>是否显示</th>
				      <th>是否启用</th>
				      <th>描述</th>
				      <th>修改/删除</th>
				      <th style="width: 3.5em;"></th>
				    </tr>
				  </thead>
				  <tbody>
				  	<%
				  		List<Resource> resources = (List<Resource>) request.getAttribute("resourceList");
				  		for(int index = 0; index<resources.size(); index++){
				  			Resource resource = resources.get(index);
				  	%>
				    <tr>
				      <td><%= resource.getSid() %></td>
				      <td><%= resource.getParentId() %></td>
				      <td><%= resource.getName() %></td>
				      <td><%= resource.getUrl() %></td>
				      <td><%= resource.getCksPower() %></td>
				      <td><%= resource.getDispalyStatus() %></td>
				      <td><%= resource.getEnableStatus() %></td>
				      <td><%= resource.getRemarks() %></td>
				      <td>
				          <a href="user.html"><i class="fa fa-pencil"></i></a>
				          <a href="#myModal" role="button" data-toggle="modal"><i class="fa fa-trash-o"></i></a>
				      </td>
				    </tr>
				    <%
				  		}
				    %>
				  </tbody>
				</table>
		</div>
<!-- ************************************************************************************** -->

            <footer>
                <hr>

                <!-- Purchase a site license to remove this link from the footer: http://www.portnine.com/bootstrap-themes -->
                <p class="pull-right">A <a href="http://www.portnine.com/bootstrap-themes" target="_blank">Free Bootstrap Theme</a> by <a href="http://www.portnine.com" target="_blank">Portnine</a></p>
                <p>© 2014 <a href="http://www.portnine.com" target="_blank">Portnine</a></p>
            </footer>
        </div>
    </div>


    <script src="${serverRoot}/static/lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>
    
  
</body></html>
