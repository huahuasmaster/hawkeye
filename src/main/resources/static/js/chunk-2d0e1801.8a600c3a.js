(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0e1801"],{"7b66":function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("material-card",{attrs:{color:"green"}},[a("template",{slot:"header"},[a("v-container",{staticStyle:{padding:"0px"},attrs:{"fill-height":"",fluid:"","grid-list-xl":""}},[a("v-layout",{attrs:{wrap:""}},[a("v-flex",[a("h4",{staticClass:"title font-weight-light mb-2",domProps:{textContent:t._s("MYSQL数据源")}}),a("p",{staticClass:"category font-weight-thin",domProps:{textContent:t._s("需要数据库开启binlog")}})]),a("v-spacer"),a("v-flex",[a("v-btn",{staticStyle:{float:"right"},attrs:{color:"success"},on:{click:t.newDatasource}},[t._v("新增")])],1)],1)],1)],1),a("v-data-table",{attrs:{headers:t.headers,items:t.items,"hide-actions":""},scopedSlots:t._u([{key:"headerCell",fn:function(e){var n=e.header;return[a("span",{staticClass:"font-weight-light text-warning text--darken-3",domProps:{textContent:t._s(n.text)}})]}},{key:"items",fn:function(e){e.index;var n=e.item;return[a("td",[t._v(t._s(n.id))]),a("td",[t._v(t._s(n.name))]),a("td",[t._v(t._s(JSON.stringify(n.config)))]),a("td",[a("v-switch",{model:{value:n.enable,callback:function(e){t.$set(n,"enable",e)},expression:"item.enable"}})],1),a("td",{staticClass:"justify-center layout px-0"},[a("v-icon",{staticClass:"mr-2",attrs:{color:"success"},on:{click:function(e){return t.editMysql(n)}}},[t._v("\n                        mdi-pencil\n                    ")]),a("v-icon",{attrs:{color:"error"},on:{click:function(e){return t.deleteMysql(n)}}},[t._v("\n                        mdi-close\n                    ")])],1)]}}])})],2)],1)},l=[],s={data:function(){return{headers:[{sortable:!1,text:"ID",value:"id",align:"left"},{sortable:!1,text:"名称",value:"name",align:"left"},{sortable:!1,text:"描述",value:"desc",align:"left"},{sortable:!1,text:"数据源",value:"datasourceName",align:"left"},{sortable:!1,text:"预聚合粒度",value:"queryGranularity",align:"left"},{sortable:!1,text:"状态",value:"enable",align:"left"},{text:"Actions",value:"name",sortable:!1,align:"center"}]}}},i=s,r=a("2877"),o=Object(r["a"])(i,n,l,!1,null,null,null);e["default"]=o.exports}}]);