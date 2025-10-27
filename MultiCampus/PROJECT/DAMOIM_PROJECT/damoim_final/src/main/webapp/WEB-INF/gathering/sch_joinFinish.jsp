<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	type="text/css">
<script src="https://kit.fontawesome.com/cfae7cf239.js"
	crossorigin="anonymous"></script>
<title>Congratulation!!</title>
<style type="text/css">
#myicon{
	color:#99E0BB;
}
.card-body {
	flex: 1 1 auto;
	padding: 1.25rem;
}

.forms-wizard {
	margin: 0;
	padding: 0;
	list-style-type: none;
	width: 100%;
	display: table;
	table-layout: fixed;
	border-radius: .25rem;
	border: 0;
}

.no-results {
	padding: 1.5rem;
	text-align: center;
}

body {
	font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto,
		"Helvetica Neue", Arial, "Noto Sans", sans-serif, "Apple Color Emoji",
		"Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
	font-size: .88rem;
	font-weight: 400;
	line-height: 1.5;
	color: #495057;
}

.swal2-icon.swal2-success {
	border-color: #a5dc86;
	position: absolute;
	width: 3.75em;
	height: 7.5em;
}

.swal2-icon.swal2-success [class^=swal2-success-circular-line][class$=left]
	{
	top: -.4375em;
	left: -2.0635em;
	transform: rotate(-45deg);
	transform-origin: 3.75em 3.75em;
	border-radius: 7.5em 0 0 7.5em;
}

.swal2-icon.swal2-success [class^=swal2-success-line][class$=tip] {
	top: 2.875em;
	left: .875em;
	width: 1.5625em;
	-webkit-transform: rotate(45deg);
	transform: rotate(45deg);
}

.swal2-icon.swal2-success [class^=swal2-success-line] {
	display: block;
	position: absolute;
	height: .3125em;
	border-radius: .125em;
	background-color: #a5dc86;
	z-index: 2;
}

.swal2-animate-success-icon .swal2-success-line-tip {
	animation: swal2-animate-success-line-tip .75s;
}

[class^=swal2] {
	-webkit-tap-highlight-color: transparent;
}

.swal2-icon {
	position: relative;
	justify-content: center;
	width: 5em;
	height: 5em;
	margin: 1.25em auto 1.875em;
	border: .25em solid transparent;
	border-radius: 50%;
	cursor: default;
	box-sizing: content-box;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	zoom: normal;
}

.no-results .results-subtitle {
	color: #adb5bd;
	font-size: 1.1rem;
}

.no-results .results-title {
	color: #495057;
	font-size: 1.25rem;
}

.mb-3, .my-3 {
	margin-bottom: 1rem !important;
}

.mt-3, .my-3 {
	margin-top: 1rem !important;
}

.mt-4, .my-4 {
	margin-top: 1.5rem !important;
}

.text-center {
	text-align: center !important;
}

.btn
:not
 
(
:disabled
 
)
:not
 
(
.disabled
 
)
{
cursor
:
 
pointer
;


}
.btn-success.btn-shadow {
	box-shadow: 0 0.125rem 0.625rem rgba(58, 196, 125, 0.4), 0 0.0625rem
		0.125rem rgba(58, 196, 125, 0.5);
}

.btn-lg.btn-wide, .btn-group-lg>.btn-wide.btn {
	padding: .5rem 2rem;
	font-size: 1.1rem;
	line-height: 1.5;
	border-radius: .3rem;
}

.btn {
	display: inline-block;
	position: relative;
	transition: color 0.15s, background-color 0.15s, border-color 0.15s,
		box-shadow 0.15s;
	text-align: center;
	vertical-align: middle;
	user-select: none;
	border: 1px solid transparent;
}

button, [type="button"], [type="reset"], [type="submit"] {
	-webkit-appearance: button;
}

button, select {
	text-transform: none;
}

button, input {
	overflow: visible;
}

button {
	-webkit-writing-mode: horizontal-tb !important;
	text-rendering: auto;
	letter-spacing: normal;
	word-spacing: normal;
	text-indent: 0px;
	text-shadow: none;
	align-items: flex-start;
	font: 400 13.3333px Arial;
}

.btn-success {
	color: #fff;
	background-color: #FF399B;
	border-color: #FF399B;
}

.btn {
	font-weight: 500;
}

a, button, .btn {
	outline: none !important;
}
</style>
</head>
<body>
	<br/><br/><br/>
	<div class="card-body">
		<div id="smartwizard" class="sw-main sw-theme-default">
			<div class="form-wizard-content sw-container tab-content"
				style="min-height: 285.406px;">
				<div id="step-3" class="tab-pane step-content"
					style="display: block;">
					<div class="no-results">
						<div>
							<span id="myicon"><i class="fas fa-check fa-6x"></i></span>
						</div>
						<div class="results-subtitle mt-4">congratulation!</div>
						<div class="results-title">참석이 완료되었습니다!</div>
						<div class="mt-3 mb-3"></div>
						<div class="text-center">
							<button class="btn-shadow btn-wide btn btn-success btn-lg" onclick="window.close()">Finish</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="divider"></div>
	</div>
</body>
</html>