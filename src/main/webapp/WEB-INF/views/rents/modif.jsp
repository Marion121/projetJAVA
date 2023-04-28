<%--
  Created by IntelliJ IDEA.
  User: mario
  Date: 28/04/2023
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <%@ include file="/WEB-INF/views/common/header.jsp" %>
  <!-- Left side column. contains the logo and sidebar -->
  <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Reservations
      </h1>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-md-12">
          <!-- Horizontal Form -->
          <div class="box">
            <!-- form start -->
            <form class="form-horizontal" method="post">
              <div class="box-body">
                <div class="form-group">
                  <label for="vehicles" class="col-sm-2 control-label">Voiture</label>

                  <div class="col-sm-10">
                    <select class="form-control" id="vehicles" name="vehicles">
                      <c:forEach items="${vehicles}" var="vehicles">
                        <option value="${vehicles.id}"> ${vehicles.constructeur} ${vehicles.nb_places}</option>
                      </c:forEach>
                    </select>
                  </div>
                </div>
                <div class="form-group">
                  <label for="clients" class="col-sm-2 control-label">Client</label>

                  <div class="col-sm-10">
                    <select class="form-control" id="clients" name="clients">
                      <c:forEach items="${clients}" var="clients">
                        <option value="${clients.id}"> ${clients.nom} ${clients.prenom}</option>
                      </c:forEach>
                    </select>
                  </div>
                </div>
                <div class="form-group">
                  <label for="begin" class="col-sm-2 control-label">Date de debut</label>

                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="begin" name="begin" required
                           data-inputmask="'alias': 'dd/mm/yyyy'" data-mask placeholder="${reservation.debut}">
                  </div>
                </div>
                <div class="form-group">
                  <label for="end" class="col-sm-2 control-label">Date de fin</label>

                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="end" name="end" required
                           data-inputmask="'alias': 'dd/mm/yyyy'" data-mask placeholder="${reservation.fin}">
                  </div>
                </div>
              </div>
              <!-- /.box-body -->
              <div class="box-footer">
                <button type="submit" class="btn btn-info pull-right">Ajouter</button>
              </div>
              <!-- /.box-footer -->
            </form>
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
    </section>
    <!-- /.content -->
  </div>

  <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script>
  $(function () {
    $('[data-mask]').inputmask()
  });
</script>
</body>
</html>

