<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><spring:message code="page.index.title"/></title>
    <%@ include file="header.jsp" %>
</head>
<body>

<%@ include file="navbar.jsp" %>

<header>
    <h1 class="text-center"></h1>
</header>

<nav class="search col-xs-2">

    <form role="search">
        <h2>
            Search
            <button type="submit" class="btn btn-primary pull-right">
                <i class="glyphicon glyphicon-search"></i>
            </button>
        </h2>

        <div class="form-group">
            <input type="text" class="form-control" placeholder="Enter ZSNumber" name="dev-search" id="dev-search">
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="srch-grade">Grade</label>
            <div>
                <select id="srch-grade"
                        class="form-control input-sm">
                    <option>any</option>
                    <option>junior</option>
                    <option>middle</option>
                    <option>senior</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="srch-mpl">Main programming language</label>
            <div>
                <select id="srch-mpl"
                        class="form-control input-sm">
                    <option>---</option>
                    <option>java SE</option>
                    <option>java EE</option>
                    <option>Native Android</option>
                    <option>Native iOS</option>
                    <option>Python</option>
                    <option>PHP</option>
                    <option>C#</option>
                    <option>C++</option>
                    <option>C</option>
                    <option>HTML/CSS</option>
                    <option>javascript</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="srch-spl">Second programming language</label>
            <div>
                <select id="srch-spl"
                        class="form-control input-sm">
                    <option>---</option>
                    <option>java SE</option>
                    <option>java EE</option>
                    <option>Native Android</option>
                    <option>Native iOS</option>
                    <option>Python</option>
                    <option>PHP</option>
                    <option>C#</option>
                    <option>C++</option>
                    <option>C</option>
                    <option>HTML/CSS</option>
                    <option>javascript</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="srch-frameworks">Frameworks</label>
            <div>
                <select id="srch-frameworks"
                        class="form-control input-sm">
                    <option>---</option>
                    <option>Spring</option>
                    <option>Play Framework</option>
                    <option>Grails</option>
                    <option>Vaadin</option>
                    <option>GWT</option>
                    <option>JSF</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="rating-cd">Rating code doctor test, %</label>
            <div>
                <input type="number"
                       min="0"
                       max="100"
                       class="form-control input-sm"
                       id="rating-cd">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="country-origin">Country of origin</label>
            <div>
                <select class="form-control input-sm"
                        id="country-origin">
                    <option>---</option>
                    <option>country 1</option>
                    <option>country 2</option>
                    <option>country 3</option>
                    <option>country 4</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="country-preferred">Preferred countries to work in by the developer</label>
            <div>
                <select class="form-control input-sm"
                        id="country-preferred"
                        multiple>
                    <option>country 1</option>
                    <option>country 2</option>
                    <option>country 3</option>
                    <option>country 4</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="permnanent-frelance">Looking for permanent or freelance contract</label>
            <div>
                <select class="form-control input-sm"
                        id="permnanent-frelance">
                    <option>---</option>
                    <option>permanent</option>
                    <option>freelance</option>
                </select>
            </div>
        </div>

        <div class="form-check">
            <label class="control-label"
            >VISA needed</label>
            <div class="radio">
                <label>
                    <input type="radio"
                           name="visa"
                           checked
                           class="form-check-input">
                    Any
                </label>
            </div>
            <div class="radio">
                <label>
                    <input type="radio"
                           name="visa"
                           class="form-check-input">
                    Yes
                </label>
            </div>
            <div class="radio">
                <label>
                    <input type="radio"
                           name="visa"
                           class="form-check-input">
                    No
                </label>
            </div>
        </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="company-type">Preferred company type</label>
            <div>
                <select class="form-control input-sm"
                        id="company-type">
                    <option>startup</option>
                    <option>mid size</option>
                    <option>corporate</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="experience">Experience in developing, years</label>
            <div>
                <input type="number"
                       id="experience"
                       min="0"
                       class="form-control input-sm">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="role">Preferred role</label>
            <div>
                <select class="form-control input-sm"
                        id="role">
                    <option>---</option>
                    <option>developer</option>
                    <option>team lead</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="worked-before">Companies worked before</label>
            <div>
                            <textarea class="form-control  input-sm"
                                      id="worked-before"
                                      maxlength="500"
                                      placeholder="max 500 characters"></textarea>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="english-level">English level</label>
            <div>
                <input type="number"
                       min="1"
                       max="5"
                       placeholder="level 1-5"
                       class="form-control input-sm"
                       id="english-level">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="travel">Willing to travel maximum xx minutes per day</label>
            <div>
                <input type="number"
                       class="form-control input-sm"
                       id="travel">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="place-live">Preferred place to live in Netherlands</label>
            <div>
                            <textarea class="form-control input-sm"
                                      id="place-live"></textarea>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="availability">Available in # weeks (notice period)</label>
            <div>
                <input type="number"
                       class="form-control input-sm"
                       id="availability">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="hours-week">Amount hours working per week</label>
            <div>
                <input type="number"
                       class="form-control input-sm"
                       id="hours-week">
            </div>
        </div>

    </form>
</nav>


<div class="col-xs-10 wrapper dev-list">
    <ul class="list-unstyled">
        <li class="person col-sm-6">
            <a href="developer-detailed.html" class="btn btn-info custom-tooltip" title="Read more">
                <i class="glyphicon glyphicon-eye-open"></i>
            </a>
            <button class="btn btn-success custom-tooltip" title="Edit">
                <i class="glyphicon glyphicon-pencil"></i>
            </button>

            <table class="table table-striped">
                <tr>
                    <th>Grade</th>
                    <td>junior</td>
                </tr>
                <tr>
                    <th>Main programming language</th>
                    <td>jave SE</td>
                </tr>
                <tr>
                    <th>Second programming language</th>
                    <td>Android Native</td>
                </tr>
                <tr>
                    <th>Rating code doctor test, %</th>
                    <td>84%</td>
                </tr>
                <tr>
                    <th>Country of origin</th>
                    <td>Moldova</td>
                </tr>

            </table>
        </li>
        <li class="person col-sm-6">
            <a href="developer-detailed.html" class="btn btn-info custom-tooltip" title="Read more">
                <i class="glyphicon glyphicon-eye-open"></i>
            </a>
            <button class="btn btn-success custom-tooltip" title="Edit">
                <i class="glyphicon glyphicon-pencil"></i>
            </button>

            <table class="table table-striped">
                <tr>
                    <th>Grade</th>
                    <td>junior</td>
                </tr>
                <tr>
                    <th>Main programming language</th>
                    <td>jave SE</td>
                </tr>
                <tr>
                    <th>Second programming language</th>
                    <td>Android Native</td>
                </tr>
                <tr>
                    <th>Rating code doctor test, %</th>
                    <td>84%</td>
                </tr>
                <tr>
                    <th>Country of origin</th>
                    <td>Moldova</td>
                </tr>

            </table>
        </li>
        <li class="person col-sm-6">
            <a href="developer-detailed.html" class="btn btn-info custom-tooltip" title="Read more">
                <i class="glyphicon glyphicon-eye-open"></i>
            </a>
            <button class="btn btn-success custom-tooltip" title="Edit">
                <i class="glyphicon glyphicon-pencil"></i>
            </button>

            <table class="table table-striped">
                <tr>
                    <th>Grade</th>
                    <td>junior</td>
                </tr>
                <tr>
                    <th>Main programming language</th>
                    <td>jave SE</td>
                </tr>
                <tr>
                    <th>Second programming language</th>
                    <td>Android Native</td>
                </tr>
                <tr>
                    <th>Rating code doctor test, %</th>
                    <td>84%</td>
                </tr>
                <tr>
                    <th>Country of origin</th>
                    <td>Moldova</td>
                </tr>

            </table>
        </li>
        <li class="person col-sm-6">
            <a href="developer-detailed.html" class="btn btn-info custom-tooltip" title="Read more">
                <i class="glyphicon glyphicon-eye-open"></i>
            </a>
            <button class="btn btn-success custom-tooltip" title="Edit">
                <i class="glyphicon glyphicon-pencil"></i>
            </button>

            <table class="table table-striped">
                <tr>
                    <th>Grade</th>
                    <td>junior</td>
                </tr>
                <tr>
                    <th>Main programming language</th>
                    <td>jave SE</td>
                </tr>
                <tr>
                    <th>Second programming language</th>
                    <td>Android Native</td>
                </tr>
                <tr>
                    <th>Rating code doctor test, %</th>
                    <td>84%</td>
                </tr>
                <tr>
                    <th>Country of origin</th>
                    <td>Moldova</td>
                </tr>

            </table>
        </li>
        <li class="person col-sm-6">
            <a href="developer-detailed.html" class="btn btn-info custom-tooltip" title="Read more">
                <i class="glyphicon glyphicon-eye-open"></i>
            </a>
            <button class="btn btn-success custom-tooltip" title="Edit">
                <i class="glyphicon glyphicon-pencil"></i>
            </button>

            <table class="table table-striped">
                <tr>
                    <th>Grade</th>
                    <td>junior</td>
                </tr>
                <tr>
                    <th>Main programming language</th>
                    <td>jave SE</td>
                </tr>
                <tr>
                    <th>Second programming language</th>
                    <td>Android Native</td>
                </tr>
                <tr>
                    <th>Rating code doctor test, %</th>
                    <td>84%</td>
                </tr>
                <tr>
                    <th>Country of origin</th>
                    <td>Moldova</td>
                </tr>

            </table>
        </li>
        <li class="person col-sm-6">
            <a href="developer-detailed.html" class="btn btn-info custom-tooltip" title="Read more">
                <i class="glyphicon glyphicon-eye-open"></i>
            </a>
            <button class="btn btn-success custom-tooltip" title="Edit">
                <i class="glyphicon glyphicon-pencil"></i>
            </button>

            <table class="table table-striped">
                <tr>
                    <th>Grade</th>
                    <td>junior</td>
                </tr>
                <tr>
                    <th>Main programming language</th>
                    <td>jave SE</td>
                </tr>
                <tr>
                    <th>Second programming language</th>
                    <td>Android Native</td>
                </tr>
                <tr>
                    <th>Rating code doctor test, %</th>
                    <td>84%</td>
                </tr>
                <tr>
                    <th>Country of origin</th>
                    <td>Moldova</td>
                </tr>

            </table>
        </li>
        <li class="person col-sm-6">
            <a href="developer-detailed.html" class="btn btn-info custom-tooltip" title="Read more">
                <i class="glyphicon glyphicon-eye-open"></i>
            </a>
            <button class="btn btn-success custom-tooltip" title="Edit">
                <i class="glyphicon glyphicon-pencil"></i>
            </button>

            <table class="table table-striped">
                <tr>
                    <th>Grade</th>
                    <td>junior</td>
                </tr>
                <tr>
                    <th>Main programming language</th>
                    <td>jave SE</td>
                </tr>
                <tr>
                    <th>Second programming language</th>
                    <td>Android Native</td>
                </tr>
                <tr>
                    <th>Rating code doctor test, %</th>
                    <td>84%</td>
                </tr>
                <tr>
                    <th>Country of origin</th>
                    <td>Moldova</td>
                </tr>

            </table>
        </li>
        <li class="person col-sm-6">
            <a href="developer-detailed.html" class="btn btn-info custom-tooltip" title="Read more">
                <i class="glyphicon glyphicon-eye-open"></i>
            </a>
            <button class="btn btn-success custom-tooltip" title="Edit">
                <i class="glyphicon glyphicon-pencil"></i>
            </button>

            <table class="table table-striped">
                <tr>
                    <th>Grade</th>
                    <td>junior</td>
                </tr>
                <tr>
                    <th>Main programming language</th>
                    <td>jave SE</td>
                </tr>
                <tr>
                    <th>Second programming language</th>
                    <td>Android Native</td>
                </tr>
                <tr>
                    <th>Rating code doctor test, %</th>
                    <td>84%</td>
                </tr>
                <tr>
                    <th>Country of origin</th>
                    <td>Moldova</td>
                </tr>

            </table>
        </li>
        <li class="person col-sm-6">
            <a href="developer-detailed.html" class="btn btn-info custom-tooltip" title="Read more">
                <i class="glyphicon glyphicon-eye-open"></i>
            </a>
            <button class="btn btn-success custom-tooltip" title="Edit">
                <i class="glyphicon glyphicon-pencil"></i>
            </button>

            <table class="table table-striped">
                <tr>
                    <th>Grade</th>
                    <td>junior</td>
                </tr>
                <tr>
                    <th>Main programming language</th>
                    <td>jave SE</td>
                </tr>
                <tr>
                    <th>Second programming language</th>
                    <td>Android Native</td>
                </tr>
                <tr>
                    <th>Rating code doctor test, %</th>
                    <td>84%</td>
                </tr>
                <tr>
                    <th>Country of origin</th>
                    <td>Moldova</td>
                </tr>

            </table>
        </li>
        <li class="person col-sm-6">
            <a href="developer-detailed.html" class="btn btn-info custom-tooltip" title="Read more">
                <i class="glyphicon glyphicon-eye-open"></i>
            </a>
            <button class="btn btn-success custom-tooltip" title="Edit">
                <i class="glyphicon glyphicon-pencil"></i>
            </button>

            <table class="table table-striped">
                <tr>
                    <th>Grade</th>
                    <td>junior</td>
                </tr>
                <tr>
                    <th>Main programming language</th>
                    <td>jave SE</td>
                </tr>
                <tr>
                    <th>Second programming language</th>
                    <td>Android Native</td>
                </tr>
                <tr>
                    <th>Rating code doctor test, %</th>
                    <td>84%</td>
                </tr>
                <tr>
                    <th>Country of origin</th>
                    <td>Moldova</td>
                </tr>

            </table>
        </li>
    </ul>
</div>

</body>

<!--
<html>
<head>
    <title>ZooStation database</title>
</head>
<body>
    <h1>Hello World!</h1>
</body>
</html>
-->
