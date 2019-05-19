(function (t) {
    function e(e) {
        for (var s, n, o = e[0], c = e[1], l = e[2], u = 0, f = []; u < o.length; u++) n = o[u], i[n] && f.push(i[n][0]), i[n] = 0;
        for (s in c) Object.prototype.hasOwnProperty.call(c, s) && (t[s] = c[s]);
        d && d(e);
        while (f.length) f.shift()();
        return r.push.apply(r, l || []), a()
    }

    function a() {
        for (var t, e = 0; e < r.length; e++) {
            for (var a = r[e], s = !0, n = 1; n < a.length; n++) {
                var c = a[n];
                0 !== i[c] && (s = !1)
            }
            s && (r.splice(e--, 1), t = o(o.s = a[0]))
        }
        return t
    }

    var s = {}, i = {app: 0}, r = [];

    function n(t) {
        return o.p + "js/" + ({}[t] || t) + "." + {
            "chunk-2d0e1801": "8a600c3a",
            "chunk-2d20ef09": "d2a95369",
            "chunk-2d213e5c": "957334f1",
            "chunk-f5d26d7c": "67ab43d8"
        }[t] + ".js"
    }

    function o(e) {
        if (s[e]) return s[e].exports;
        var a = s[e] = {i: e, l: !1, exports: {}};
        return t[e].call(a.exports, a, a.exports, o), a.l = !0, a.exports
    }

    o.e = function (t) {
        var e = [], a = i[t];
        if (0 !== a) if (a) e.push(a[2]); else {
            var s = new Promise(function (e, s) {
                a = i[t] = [e, s]
            });
            e.push(a[2] = s);
            var r, c = document.createElement("script");
            c.charset = "utf-8", c.timeout = 120, o.nc && c.setAttribute("nonce", o.nc), c.src = n(t), r = function (e) {
                c.onerror = c.onload = null, clearTimeout(l);
                var a = i[t];
                if (0 !== a) {
                    if (a) {
                        var s = e && ("load" === e.type ? "missing" : e.type), r = e && e.target && e.target.src,
                            n = new Error("Loading chunk " + t + " failed.\n(" + s + ": " + r + ")");
                        n.type = s, n.request = r, a[1](n)
                    }
                    i[t] = void 0
                }
            };
            var l = setTimeout(function () {
                r({type: "timeout", target: c})
            }, 12e4);
            c.onerror = c.onload = r, document.head.appendChild(c)
        }
        return Promise.all(e)
    }, o.m = t, o.c = s, o.d = function (t, e, a) {
        o.o(t, e) || Object.defineProperty(t, e, {enumerable: !0, get: a})
    }, o.r = function (t) {
        "undefined" !== typeof Symbol && Symbol.toStringTag && Object.defineProperty(t, Symbol.toStringTag, {value: "Module"}), Object.defineProperty(t, "__esModule", {value: !0})
    }, o.t = function (t, e) {
        if (1 & e && (t = o(t)), 8 & e) return t;
        if (4 & e && "object" === typeof t && t && t.__esModule) return t;
        var a = Object.create(null);
        if (o.r(a), Object.defineProperty(a, "default", {
            enumerable: !0,
            value: t
        }), 2 & e && "string" != typeof t) for (var s in t) o.d(a, s, function (e) {
            return t[e]
        }.bind(null, s));
        return a
    }, o.n = function (t) {
        var e = t && t.__esModule ? function () {
            return t["default"]
        } : function () {
            return t
        };
        return o.d(e, "a", e), e
    }, o.o = function (t, e) {
        return Object.prototype.hasOwnProperty.call(t, e)
    }, o.p = "/static/", o.oe = function (t) {
        throw console.error(t), t
    };
    var c = window["webpackJsonp"] = window["webpackJsonp"] || [], l = c.push.bind(c);
    c.push = e, c = c.slice();
    for (var u = 0; u < c.length; u++) e(c[u]);
    var d = l;
    r.push([0, "chunk-vendors"]), a()
})({
    0: function (t, e, a) {
        t.exports = a("56d7")
    }, "0a2d": function (t) {
        t.exports = {needHelp: "Need Help?"}
    }, 1185: function (t, e, a) {
        "use strict";
        a.r(e);
        var s = function () {
            var t = this, e = t.$createElement, a = t._self._c || e;
            return a("v-expansion-panel", t._l(t.items, function (e) {
                return a("v-expansion-panel-content", {key: e.id}, [a("template", {slot: "header"}, [a("span", [t._v(t._s(e.queryName)), a("span", {staticStyle: {"font-style": "italic"}}, [t._v("(" + t._s(t._f("getDataType")(e)) + ")")]), t._v(" ：" + t._s(e.desc))]), a("span", {
                    staticStyle: {
                        float: "right",
                        "margin-right": "20px"
                    }
                })]), a("v-list", {attrs: {"two-line": ""}}, [a("v-list-tile", {
                    on: {
                        click: function (t) {
                        }
                    }
                }, [a("v-list-tile-content", [a("v-list-tile-title", [t._v("来源")]), a("v-list-tile-sub-title", [t._v(t._s(t._f("getSource")(e)))])], 1)], 1), a("v-divider"), a("v-list-tile", {
                    attrs: {"one-line": ""},
                    on: {
                        click: function (t) {
                        }
                    }
                }, [a("v-list-tile-content", [a("v-list-tile-title", [t._v("状态")])], 1), a("v-spacer"), a("v-switch", {
                    staticStyle: {"max-width": "40px"},
                    on: {
                        change: function (a) {
                            return t.changeState(e)
                        }
                    },
                    model: {
                        value: e.enable, callback: function (a) {
                            t.$set(e, "enable", a)
                        }, expression: "item.enable"
                    }
                })], 1), a("v-divider"), a("v-list-tile", {
                    on: {
                        click: function (t) {
                        }
                    }
                }, [a("v-list-tile-content", [a("v-list-tile-title", [t._v("创建时间")]), a("v-list-tile-sub-title", [t._v(t._s(t._f("getDate")(e)))])], 1)], 1)], 1)], 2)
            }), 1)
        }, i = [], r = (a("c1df"), {
            filters: {
                getDataType: function (t) {
                    return t.distinct ? "DISTINCT" : t.dataType.toUpperCase()
                }, getSource: function (t) {
                    return t.fileName ? "使用了原始字段 - ".concat(t.fileName) : "自定义脚本产生"
                }, getDate: function (t) {
                    return new Date(t.createDate).toDateString()
                }
            }, data: function () {
                return {
                    items: [{
                        id: 1,
                        desc: "desc",
                        queryName: "das",
                        distinct: !0,
                        fileName: "da",
                        dataType: "STRING",
                        variableType: "dimension",
                        enable: !0,
                        createDate: 1554886036e3
                    }]
                }
            }, methods: {
                changeState: function (t) {
                    console.log(t)
                }
            }
        }), n = r, o = a("2877"), c = Object(o["a"])(n, s, i, !1, null, null, null);
        e["default"] = c.exports
    }, 1196: function (t, e, a) {
        "use strict";
        var s = a("1d1c"), i = a.n(s);
        i.a
    }, "173d": function (t) {
        t.exports = {facebook: "Facebook", footer: "Footer", github: "Github", twitter: "Twitter"}
    }, "1a5d": function (t, e, a) {
        var s = {
            "./ChartsTest.vue": ["b203", "chunk-2d20ef09"],
            "./DashboardV2.vue": ["e552", "chunk-f5d26d7c"],
            "./Datasource.vue": ["af45", "chunk-2d213e5c"],
            "./MetricVariable.vue": ["7b66", "chunk-2d0e1801"]
        };

        function i(t) {
            var e = s[t];
            return e ? a.e(e[1]).then(function () {
                var t = e[0];
                return a(t)
            }) : Promise.resolve().then(function () {
                var e = new Error("Cannot find module '" + t + "'");
                throw e.code = "MODULE_NOT_FOUND", e
            })
        }

        i.keys = function () {
            return Object.keys(s)
        }, i.id = "1a5d", t.exports = i
    }, "1d1c": function (t, e, a) {
    }, "20ee": function (t, e, a) {
        "use strict";
        a.r(e);
        var s = function () {
                var t = this, e = t.$createElement, a = t._self._c || e;
                return a("div", ["stats" !== t.chartDetail.type ? a("material-card", t._g(t._b({
                    ref: "chart-content",
                    staticClass: "v-card--material-chart"
                }, "material-card", t.$attrs, !1), t.$listeners), [a("div", {staticClass: "mycontent"}, ["line" === t.chartDetail.type ? a("ve-line", {
                    ref: "chart",
                    attrs: {
                        slot: "header",
                        data: t.chartData,
                        colors: t.colors,
                        dataZoom: t.dataZoom,
                        loading: !t.readyToShow,
                        "judge-width": "",
                        toolbox: t.toolbox
                    },
                    slot: "header"
                }) : t._e(), "pie" === t.chartDetail.type ? a("ve-pie", {
                    ref: "chart",
                    attrs: {
                        slot: "header",
                        data: t.chartData,
                        colors: t.colors,
                        "judge-width": "",
                        chartSetting: t.chartSetting
                    },
                    slot: "header"
                }) : t._e(), "funnel" === t.chartDetail.type ? a("ve-funnel", {
                    ref: "chart",
                    attrs: {
                        slot: "header",
                        data: t.chartData,
                        colors: t.colors,
                        "judge-width": "",
                        chartSetting: {useDefaultOrder: !0, filterZero: !0}
                    },
                    slot: "header"
                }) : t._e()], 1), a("v-container", {
                    ref: "chart-detail",
                    staticStyle: {padding: "0px"},
                    attrs: {"align-space-around": "", "justify-center": "", row: ""}
                }, [a("v-layout", [a("v-flex", [a("h4", {
                    ref: "chart-name",
                    staticClass: "title font-weight-light"
                }, [t._v(t._s(t.chartDetail.name))]), a("p", {
                    ref: "chart-desc",
                    staticClass: "category d-inline-flex font-weight-light"
                }, [t._v(t._s(t.chartDetail.desc))])]), a("v-spacer"), "line" === this.chartDetail.type ? a("v-flex", {
                    staticStyle: {"margin-right": "32px"},
                    attrs: {lg3: "", md3: ""}
                }, [a("v-select", {
                    attrs: {items: t.compareTime, label: "选择同比", "item-text": "t", "item-value": "v"},
                    model: {
                        value: t.compareText, callback: function (e) {
                            t.compareText = e
                        }, expression: "compareText"
                    }
                })], 1) : t._e(), a("v-menu", {
                    attrs: {"offset-y": ""},
                    scopedSlots: t._u([{
                        key: "activator", fn: function (e) {
                            var s = e.on;
                            return [a("v-icon", t._g({attrs: {color: "info"}}, s), [t._v("\n                            mdi-pencil\n                        ")])]
                        }
                    }], null, !1, 4246835115)
                }, [a("v-list", [a("v-list-tile", {
                    key: "edit",
                    on: {click: t.edit}
                }, [a("v-list-tile-title", [t._v("编辑")])], 1), a("v-list-tile", {
                    key: "delete", on: {
                        click: function (e) {
                            return t.delete1()
                        }
                    }
                }, [a("v-list-tile-title", [t._v("删除")])], 1)], 1)], 1)], 1)], 1)], 1) : t._e(), "stats" === t.chartDetail.type ? a("material-stats-card", {
                    ref: "chart-stats",
                    attrs: {
                        color: "green",
                        icon: "mdi-store",
                        title: t.chartDetail.name,
                        value: t.wholeStats,
                        "sub-icon": "mdi-calendar",
                        "sub-text": t.chartDetail.desc
                    },
                    on: {delete_it: t.delete1, edit_it: t.edit}
                }) : t._e(), a("v-dialog", {
                    attrs: {"max-width": "290"}, model: {
                        value: t.dialog, callback: function (e) {
                            t.dialog = e
                        }, expression: "dialog"
                    }
                }, [a("v-card", [a("v-card-title", {staticClass: "headline"}, [t._v("确认要删除图表?")]), a("v-card-text", [t._v("\n                该操作无法撤销\n            ")]), a("v-card-actions", [a("v-spacer"), a("v-btn", {
                    attrs: {
                        color: "green darken-1",
                        flat: "flat"
                    }, on: {
                        click: function (e) {
                            t.dialog = !1
                        }
                    }
                }, [t._v("\n                    取消\n                ")]), a("v-btn", {
                    attrs: {
                        color: "red darken-1",
                        flat: "flat"
                    }, on: {click: t.doDelete}
                }, [t._v("\n                    删除\n                ")])], 1)], 1)], 1)], 1)
            }, i = [], r = a("e814"), n = a.n(r), o = (a("a481"), a("6762"), a("2fdb"), a("2d1f")), c = a.n(o),
            l = (a("55dd"), a("6b54"), a("7f7f"), a("b6d0")), u = a.n(l), d = a("a4bb"), f = a.n(d),
            m = (a("ac6a"), a("7514"), a("f891")), p = a("c3da"), h = a.n(p), v = a("40ea"), b = a.n(v), g = a("947c"),
            y = a.n(g), x = (a("d768"), a("0a6d"), a("b11c"), a("c1df")), w = 18e5, j = 36e5, k = 216e5, _ = 432e5,
            D = 864e5, T = 2592e5, I = 6048e5, C = 80352e5, $ = [{min: -1, max: w, options: ["1分钟", "2分钟", "5分钟"]}, {
                min: w,
                max: j,
                options: ["2分钟", "5分钟", "10分钟"]
            }, {min: j, max: k, options: ["10分钟", "15分钟", "20分钟"]}, {
                min: k,
                max: _,
                options: ["15分钟", "20分钟", "30分钟"]
            }, {min: _, max: D, options: ["15分钟", "20分钟", "30分钟"]}, {
                min: D,
                max: T,
                options: ["2小时", "6小时", "12小时"]
            }, {min: T, max: I, options: ["6小时", "12小时", "1天"]}, {min: I, max: C, options: ["1天"]}],
            S = [{type: "line", height: 60}, {type: "pie", height: 70}, {type: "funnel", height: 70}], O = {
                inheritAttrs: !1,
                props: ["chartDetail", "queryInterval", "showStyle", "extraFilters"],
                components: {VeLine: h.a, VePie: b.a, VeFunnel: y.a},
                watch: {
                    queryInterval: {
                        handler: function (t, e) {
                            this.queryTime.interval.startTime = this.queryInterval.startTime, this.queryTime.interval.endTime = this.queryInterval.endTime, console.log("查询时间变化，起始时间：%s 结束时间：%s", this.queryTime.interval.startTime, this.queryTime.interval.endTime), this.getMetric()
                        }, deep: !0
                    }, compareText: function (t, e) {
                        this.getMetric()
                    }
                },
                data: function () {
                    return {
                        colors: ["#4caf50", "#82B1FF", "#f55a4e", "#495057", "#00d3ee", "#ffa21a", "#5cb860"],
                        dataZoom: [{type: "slider"}],
                        chartData: {columns: [], rows: []},
                        metricList: [],
                        queryTime: {interval: {startTime: 0, endTime: 0}, period: ""},
                        xAxis: {axisLabel: {interval: 0}},
                        compareMetricText: null,
                        compareText: null,
                        compareTime: [{v: j, t: "一小时前"}, {v: D, t: "一天前"}, {v: I, t: "一周前"}],
                        lastRefreshTime: (new Date).valueOf(),
                        chartSetting: {coordinateSystem: "geo"},
                        first: !0,
                        toolbox: {feature: {magicType: {type: ["line", "bar"]}, saveAsImage: {}}},
                        dialog: !1
                    }
                },
                computed: {
                    compareMetric: function () {
                        var t = this;
                        return this.compareTime.find(function (e) {
                            return e.v === t.compareText
                        })
                    }, readyToShow: function () {
                        return this.chartData && this.chartData.columns && this.chartData.columns.length >= 1
                    }, wholeStats: function () {
                        var t = this.chartDetail.config.prefix ? this.chartDetail.config.prefix : "",
                            e = this.chartDetail.config.suffix ? this.chartDetail.config.suffix : "";
                        return t + this.chartData + e
                    }, dimensions: function () {
                        var t = [];
                        return this.chartDetail.dimensions.forEach(function (e) {
                            return t.push(e.alias)
                        }), this.compareMetric && t.push("同比"), t
                    }, metrics: function () {
                        var t = [];
                        return this.chartDetail.aggregations.forEach(function (e) {
                            return t.push(e.alias)
                        }), t
                    }, granularityOptions: function () {
                        var t = this.queryTime.interval.endTime - this.queryTime.interval.startTime;
                        return console.log("当前时间间隔" + t + "秒"), $.find(function (e) {
                            return e.min < t && e.max >= t
                        }).options
                    }, needTimeSlice: function () {
                        return "line" === this.chartDetail.type
                    }, needSortCharts: function () {
                        return "bar" === this.chartDetail.type
                    }, dataAxis: function () {
                        var t = [];
                        return this.chartData.rows.forEach(function (e) {
                            return t.push(e["xAxis"])
                        }), t
                    }, columns: function () {
                        var t = [];
                        if (this.chartData.rows < 1) return t;
                        var e = this.dimensions.length > 1 ? "分组维度" : this.dimensions[0], a = this.needTimeSlice ? "时间" : e;
                        return t.push({
                            title: a,
                            key: "xAxis",
                            sortable: !0
                        }), f()(this.chartData.rows[0]).filter(function (t) {
                            return "xAxis" !== t
                        }).forEach(function (e) {
                            return t.push({title: e, key: e, sortable: !0})
                        }), t
                    }
                },
                methods: {
                    edit: function () {
                        this.$emit("edit_chart")
                    }, delete1: function () {
                        this.dialog = !0
                    }, doDelete: function () {
                        var t = this, e = this.$route.params.id, a = this.chartDetail.id;
                        m["b"].delete(e, a).then(function (e) {
                            t.$store.dispatch("alert", {type: "success", content: "删除成功"}), t.$emit("delete-chart")
                        }).catch(function (e) {
                            t.$store.dispatch("alert", {type: "error", content: "删除失败"})
                        }).finally(function () {
                            return t.dialog = !1
                        })
                    }, getMetric: function () {
                        var t = this;
                        if (this.chartId) {
                            console.log("更新指标数据"), this.clear();
                            var e = {
                                chartId: this.chartId,
                                intervals: [{
                                    startTime: this.queryTime.interval.startTime,
                                    endTime: this.queryTime.interval.endTime
                                }],
                                period: this.needTimeSlice ? this.queryTime.period : "all",
                                filter: {}
                            }, a = new u.a;
                            a.add("xAxis"), m["a"].getViewByChart(e.chartId, e).then(function (a) {
                                if (console.log("图表".concat(t.chartDetail.name), a), console.log(t.compareText), t.metricList = a.metricList, t.needTimeSlice && t.compareMetric) return console.log("添加同比，时间间隔", t.compareMetric.v), e.intervals = [{
                                    startTime: t.queryTime.interval.startTime - t.compareMetric.v,
                                    endTime: t.queryTime.interval.endTime - t.compareMetric.v
                                }], m["a"].getViewByChart(e.chartId, e);
                                t.calMetric()
                            }).then(function (e) {
                                e && (console.log("同比数据", e), e.metricList.forEach(function (e) {
                                    var a = t.metricList.find(function (a) {
                                        return a.timestamp === e.timestamp + t.compareMetric.v
                                    }).data;
                                    a.forEach(function (t) {
                                        return t["同比"] = "当前时间"
                                    }), e.data.forEach(function (e) {
                                        e["同比"] = t.compareMetric.t, a.push(e)
                                    })
                                }), t.calMetric())
                            }).catch(function (e) {
                                t.$store.dispatch("alert", {
                                    type: "info",
                                    content: "图表".concat(t.chartDetail.name, "数据获取失败")
                                })
                            })
                        }
                    }, calMetric: function () {
                        var t = this;
                        console.log("当前图表：" + this.chartDetail.name + "  维度：" + this.dimensions), console.log("当前图表：" + this.chartDetail.name + "  度量：" + this.metrics);
                        var e = new u.a;
                        if (e.add("xAxis"), "stats" !== this.chartDetail.type) {
                            if (this.metricList.sort(function (t, e) {
                                return t.timestamp > e.timestamp ? 1 : -1
                            }), this.needTimeSlice ? this.metricList.forEach(function (a) {
                                var s = {}, i = t.queryTime.interval.endTime - t.queryTime.interval.startTime;
                                s.xAxis = i >= D ? x(a.timestamp).format("MM/DD H:mm ") : x(a.timestamp).format("H:mm "), a.data.forEach(function (a) {
                                    var i = c()(a);
                                    i.forEach(function (a) {
                                        var r = a[0], n = a[1];
                                        if (t.metrics.includes(r)) {
                                            var o = r + "『";
                                            i.forEach(function (e) {
                                                t.dimensions.includes(e[0]) && (o = o + e[0] + ":" + e[1] + ".")
                                            }), o += "』", o = o.replace("『』", ""), s[o] = n, e.add(o)
                                        }
                                    })
                                }), t.chartData.rows.push(s)
                            }) : this.metricList[0].data.forEach(function (a) {
                                var s = {}, i = "", r = c()(a);
                                r.forEach(function (a) {
                                    var r = a[0], o = a[1];
                                    t.metrics.includes(r) && (s[r] = "funnel" === t.chartDetail.type && "数量" === r ? n()(o) : o, e.add(r)), t.dimensions.includes(r) && (i = 1 === t.dimensions.length ? o : i + r + ":" + o + ".")
                                }), s.xAxis = i, t.chartData.rows.push(s)
                            }), e.size <= 1 && this.metrics.forEach(function (t) {
                                return e.add(t)
                            }), this.chartData.rows.length < 1 && this.chartData.rows.push({}), e.forEach(function (e) {
                                t.chartData.columns.push(e), t.chartData.rows.forEach(function (t) {
                                    void 0 === t[e] && (t[e] = 0)
                                })
                            }), this.needSortCharts && this.chartData.columns.filter(function (t) {
                                return "xAxis" !== t
                            }).length > 0) {
                                var a = this.chartData.columns.filter(function (t) {
                                    return "xAxis" !== t
                                })[0];
                                this.chartData.rows.sort(function (t, e) {
                                    return t[a] > e[a] ? 1 : -1
                                })
                            }
                            console.log("图表渲染数据", this.chartData), this.xAxis.data = this.dataAxis, this.lastRefreshTime = (new Date).getTime(), this.viewReSize(this.$parent.$el.clientHeight)
                        } else if (this.metricList.length <= 0 || this.metricList[0].data.length <= 0) this.chartData = 0; else {
                            var s = this.metricList[0].data[0], i = this.metrics[0];
                            console.log("data:", s), console.log(s[i]), this.chartData = s[i].toString()
                        }
                    }, clear: function () {
                        "stats" === this.chartDetail.type ? this.chartData = 0 : (this.chartData.columns = [], this.chartData.rows = []), this.metricList = []
                    }, autoRefresh: function (t) {
                        this.queryTime.interval.startTime = this.queryTime.interval.startTime + t, this.queryTime.interval.endTime = this.queryTime.interval.endTime + t, this.getMetric()
                    }, viewReSize: function (t, e) {
                        var a = this;
                        if (console.log("".concat(this.chartDetail.name, "开始更新大小, 容器大小").concat(t)), this.chartDetail && "stats" !== this.chartDetail.type) {
                            console.log(this.$refs);
                            var s = this.$refs["chart-detail"].clientHeight, i = S.find(function (t) {
                                return t.type === a.chartDetail.type
                            }).height, r = t - s - i;
                            this.$nextTick(function (t) {
                                var e = a.$refs["chart"];
                                e && (e.height = r + "px", e.echarts.resize())
                            })
                        }
                    }
                },
                mounted: function () {
                    this.queryTime.interval.startTime = this.queryInterval.startTime, this.queryTime.interval.endTime = this.queryInterval.endTime, this.queryTime.period = this.granularityOptions[0], this.chartId = this.chartDetail.id, this.getMetric();
                    this.$parent.$el.clientHeight
                }
            }, L = O, M = (a("ad75"), a("2877")), E = Object(M["a"])(L, s, i, !1, null, null, null);
        e["default"] = E.exports
    }, "25f5": function (t, e, a) {
        "use strict";
        var s = a("453f"), i = a.n(s);
        i.a
    }, 2609: function (t, e, a) {
        "use strict";
        a.r(e), e["default"] = {
            drawer: null,
            color: "success",
            image: "https://demos.creative-tim.com/vue-material-dashboard/img/sidebar-2.32103624.jpg"
        }
    }, "2a74": function (t, e, a) {
        "use strict";
        a.r(e);
        a("28a5");
        var s = a("768b"), i = (a("a481"), a("ac6a"), a("c653")), r = {};
        i.keys().forEach(function (t) {
            if ("./index.js" !== t) {
                var e = t.replace(/(\.\/|\.js)/g, ""), a = e.split("/"), n = Object(s["a"])(a, 2), o = n[0], c = n[1];
                r[o] || (r[o] = {namespaced: !0}), r[o][c] = i(t).default
            }
        }), e["default"] = r
    }, "2c37": function (t, e, a) {
    }, 4025: function (t, e, a) {
    }, 4093: function (t, e, a) {
        "use strict";
        a.r(e);
        var s = function () {
            var t = this, e = t.$createElement, a = t._self._c || e;
            return a("v-data-table", {
                staticStyle: {"margin-right": "16px", "margin-left": "16px"},
                attrs: {headers: t.headers, items: t.items, "hide-actions": ""},
                scopedSlots: t._u([{
                    key: "headerCell", fn: function (e) {
                        var s = e.header;
                        return [a("span", {
                            staticClass: "font-weight-light text-warning text--darken-3",
                            domProps: {textContent: t._s(s.text)}
                        })]
                    }
                }, {
                    key: "items", fn: function (e) {
                        e.index;
                        var s = e.item;
                        return [a("td", [t._v(t._s(s.field))]), a("td", [a("v-select", {
                            attrs: {
                                items: t.types,
                                label: "存储类型",
                                "item-text": "text",
                                "item-value": "value"
                            }, model: {
                                value: s.type, callback: function (e) {
                                    t.$set(s, "type", e)
                                }, expression: "item.type"
                            }
                        })], 1)]
                    }
                }])
            })
        }, i = [], r = {
            props: ["fields"], watch: {
                fields: function (t, e) {
                    this.items = t
                }
            }, data: function () {
                return {
                    headers: [{sortable: !1, text: "字段名", value: "field", align: "left"}, {
                        sortable: !1,
                        text: "存储类型",
                        value: "type",
                        align: "left"
                    }],
                    types: [{text: "维度", value: "dimension"}, {text: "指标", value: "metric"}, {
                        text: "不存储",
                        value: null
                    }],
                    items: []
                }
            }, mounted: function () {
                this.items = this.fields
            }
        }, n = r, o = a("2877"), c = Object(o["a"])(n, s, i, !1, null, null, null);
        e["default"] = c.exports
    }, "41c0": function (t, e, a) {
        "use strict";
        a.r(e);
        var s = function () {
            var t = this, e = t.$createElement, a = t._self._c || e;
            return a("v-navigation-drawer", {
                attrs: {
                    id: "app-drawer",
                    app: "",
                    dark: "",
                    floating: "",
                    persistent: "",
                    "mobile-break-point": "991",
                    width: "260"
                }, model: {
                    value: t.inputValue, callback: function (e) {
                        t.inputValue = e
                    }, expression: "inputValue"
                }
            }, [a("v-img", {attrs: {src: t.image, height: "100%"}}, [a("v-layout", {
                staticClass: "fill-height",
                attrs: {tag: "v-list", column: ""}
            }, [a("v-list-tile", {attrs: {avatar: ""}}, [a("v-list-tile-avatar", {attrs: {color: "white"}}), a("v-list-tile-title", {staticClass: "title"}, [t._v("\n          鹰眼\n        ")])], 1), a("v-divider"), t.responsive ? a("v-list-tile", [a("v-text-field", {
                staticClass: "purple-input search-input",
                attrs: {label: "Search...", color: "purple"}
            })], 1) : t._e(), t._l(t.links, function (e, s) {
                return a("v-list-tile", {
                    key: s,
                    staticClass: "v-list-item",
                    attrs: {to: e.to, "active-class": t.color, avatar: ""}
                }, [a("v-list-tile-action", [a("v-icon", [t._v(t._s(e.icon))])], 1), a("v-list-tile-title", {domProps: {textContent: t._s(e.text)}})], 1)
            })], 2)], 1)], 1)
        }, i = [], r = a("cebc"), n = a("2f62"), o = {
            data: function () {
                return {
                    logo: "./img/vuetifylogo.png",
                    links: [{to: "/dashboardv2", icon: "mdi-view-dashboard", text: "看板"}, {
                        to: "/datasource",
                        icon: "mdi-dns",
                        text: "数据源"
                    }],
                    responsive: !1
                }
            },
            computed: Object(r["a"])({}, Object(n["c"])("app", ["image", "color"]), {
                inputValue: {
                    get: function () {
                        return this.$store.state.app.drawer
                    }, set: function (t) {
                        this.setDrawer(t)
                    }
                }, items: function () {
                    return this.$t("Layout.View.items")
                }
            }),
            mounted: function () {
                this.onResponsiveInverted(), window.addEventListener("resize", this.onResponsiveInverted)
            },
            beforeDestroy: function () {
                window.removeEventListener("resize", this.onResponsiveInverted)
            },
            methods: Object(r["a"])({}, Object(n["b"])("app", ["setDrawer", "toggleDrawer"]), {
                onResponsiveInverted: function () {
                    window.innerWidth < 991 ? this.responsive = !0 : this.responsive = !1
                }
            })
        }, c = o, l = (a("ff57"), a("2877")), u = Object(l["a"])(c, s, i, !1, null, null, null);
        e["default"] = u.exports
    }, "42e7": function (t, e, a) {
        "use strict";
        a.r(e);
        var s = function () {
            var t = this, e = t.$createElement, a = t._self._c || e;
            return a("material-card", t._g(t._b({staticClass: "v-card--material-stats"}, "material-card", t.$attrs, !1), t.$listeners), [a("v-card", {
                staticClass: "pa-4",
                class: "elevation-" + t.elevation,
                attrs: {slot: "offset", color: t.color, dark: ""},
                slot: "offset"
            }, [a("v-icon", {attrs: {size: "40"}}, [t._v("\n      " + t._s(t.icon) + "\n    ")])], 1), a("div", {staticClass: "text-xs-right"}, [a("p", {
                staticClass: "category grey--text font-weight-light",
                domProps: {textContent: t._s(t.title)}
            }), a("h3", {staticClass: "title display-1 font-weight-light"}, [t._v("\n      " + t._s(t.value) + " "), a("small", [t._v(t._s(t.smallValue))])])]), a("template", {slot: "actions"}, [a("v-icon", {
                staticClass: "mr-2",
                attrs: {color: t.subIconColor, size: "20"}
            }, [t._v("\n      " + t._s(t.subIcon) + "\n    ")]), a("span", {
                staticClass: "caption font-weight-light",
                class: t.subTextColor,
                domProps: {textContent: t._s(t.subText)}
            }), a("v-spacer"), a("v-menu", {
                attrs: {"offset-y": ""},
                scopedSlots: t._u([{
                    key: "activator", fn: function (e) {
                        var s = e.on;
                        return [a("v-icon", t._g({attrs: {color: "info"}}, s), [t._v("\n          mdi-pencil\n        ")])]
                    }
                }])
            }, [a("v-list", [a("v-list-tile", {
                key: "edit", on: {
                    click: function (e) {
                        return t.$emit("edit_it")
                    }
                }
            }, [a("v-list-tile-title", [t._v("编辑")])], 1), a("v-list-tile", {
                key: "delete", on: {
                    click: function (e) {
                        return t.$emit("delete_it")
                    }
                }
            }, [a("v-list-tile-title", [t._v("删除")])], 1)], 1)], 1)], 1)], 2)
        }, i = [], r = a("cebc"), n = a("e3a9"), o = {
            inheritAttrs: !1,
            props: Object(r["a"])({}, n["default"].props, {
                icon: {type: String, required: !0},
                subIcon: {type: String, default: void 0},
                subIconColor: {type: String, default: void 0},
                subTextColor: {type: String, default: void 0},
                subText: {type: String, default: void 0},
                title: {type: String, default: void 0},
                value: {type: String, default: void 0},
                smallValue: {type: String, default: void 0}
            })
        }, c = o, l = (a("4d79"), a("2877")), u = Object(l["a"])(c, s, i, !1, null, null, null);
        e["default"] = u.exports
    }, 4360: function (t, e, a) {
        "use strict";
        var s = a("2b0e"), i = a("2f62"), r = {
            alert: function (t, e) {
                var a = t.commit;
                a("doAlert", e), setTimeout(function () {
                    a("cancelAlert")
                }, 3e3)
            }
        }, n = {}, o = a("2a74"), c = {
            info: function (t, e) {
                this.alert(t, {content: e, type: "info"})
            }, error: function (t, e) {
                this.alert(t, {content: e, type: "error"})
            }, success: function (t, e) {
                this.alert(t, {content: e, type: "success"})
            }, warning: function (t, e) {
                this.alert(t, {content: e, type: "warning"})
            }, doAlert: function (t, e) {
                t.type = e.type, t.content = e.content, t.showAlert = !0
            }, cancelAlert: function (t) {
                t.content = "", t.showAlert = !1
            }
        }, l = {userId: 1, showAlert: !1, content: "", type: "info"};
        s["default"].use(i["a"]);
        var u = new i["a"].Store({actions: r, getters: n, modules: o["default"], mutations: c, state: l});
        e["a"] = u
    }, 4394: function (t, e, a) {
        "use strict";
        a.r(e);
        var s = function () {
            var t = this, e = t.$createElement, a = t._self._c || e;
            return a("div", [a("v-tabs", {
                attrs: {color: "cyan", dark: "", "slider-color": "yellow"},
                model: {
                    value: t.active, callback: function (e) {
                        t.active = e
                    }, expression: "active"
                }
            }, [a("v-tab", {
                key: "1",
                attrs: {ripple: ""}
            }, [t._v("\n            连接配置\n        ")]), a("v-tab", {
                key: "2",
                attrs: {ripple: ""}
            }, [t._v("\n            存储配置\n        ")]), a("v-tab-item", {key: "1"}, [a("form", {staticStyle: {padding: "24px"}}, [a("v-text-field", {
                attrs: {
                    label: "数据源描述",
                    required: ""
                }, model: {
                    value: t.params.desc, callback: function (e) {
                        t.$set(t.params, "desc", e)
                    }, expression: "params.desc"
                }
            }), a("v-select", {
                attrs: {items: t.types, label: "数据源类型", "item-text": "text", "item-value": "value"},
                model: {
                    value: t.params.type, callback: function (e) {
                        t.$set(t.params, "type", e)
                    }, expression: "params.type"
                }
            }), "MYSQL" === t.params.type ? a("v-text-field", {
                attrs: {label: "数据库名称", required: ""},
                model: {
                    value: t.params.sourceInfo.database, callback: function (e) {
                        t.$set(t.params.sourceInfo, "database", e)
                    }, expression: "params.sourceInfo.database"
                }
            }) : t._e(), "MYSQL" === t.params.type ? a("v-text-field", {
                attrs: {label: "表名称", required: ""},
                model: {
                    value: t.params.sourceInfo.table, callback: function (e) {
                        t.$set(t.params.sourceInfo, "table", e)
                    }, expression: "params.sourceInfo.table"
                }
            }) : t._e(), "MYSQL" === t.params.type ? a("v-text-field", {
                attrs: {
                    label: "数据库地址",
                    placeholder: "localhost:3307",
                    required: ""
                }, model: {
                    value: t.params.sourceInfo.host, callback: function (e) {
                        t.$set(t.params.sourceInfo, "host", e)
                    }, expression: "params.sourceInfo.host"
                }
            }) : t._e(), "MYSQL" === t.params.type ? a("v-text-field", {
                attrs: {
                    label: "账号",
                    required: "",
                    placeholder: "root"
                }, model: {
                    value: t.params.sourceInfo.user, callback: function (e) {
                        t.$set(t.params.sourceInfo, "user", e)
                    }, expression: "params.sourceInfo.user"
                }
            }) : t._e(), "MYSQL" === t.params.type ? a("v-text-field", {
                attrs: {
                    label: "密码",
                    required: "",
                    placeholder: "123456",
                    type: "password"
                }, model: {
                    value: t.params.sourceInfo.password, callback: function (e) {
                        t.$set(t.params.sourceInfo, "password", e)
                    }, expression: "params.sourceInfo.password"
                }
            }) : t._e(), "BURY" === t.params.type ? a("v-text-field", {
                attrs: {label: "埋点事件名称", required: ""},
                model: {
                    value: t.params.sourceInfo.event, callback: function (e) {
                        t.$set(t.params.sourceInfo, "event", e)
                    }, expression: "params.sourceInfo.event"
                }
            }) : t._e(), "BURY" === t.params.type ? a("v-textarea", {
                attrs: {label: "数据样例", required: ""},
                model: {
                    value: t.params.sample, callback: function (e) {
                        t.$set(t.params, "sample", e)
                    }, expression: "params.sample"
                }
            }) : t._e()], 1)]), a("v-tab-item", {key: "2"}, [a("VariableTable", {
                ref: "table",
                attrs: {fields: t.mappedFields}
            })], 1)], 1), a("div", {staticClass: "text-xs-center mt-3"}, [a("v-btn", {
                attrs: {
                    color: "info",
                    loading: t.btnLoading
                }, on: {click: t.next}
            }, [t._v("下一步")])], 1)], 1)
        }, i = [], r = a("4093"), n = a("f891"), o = {
            components: {VariableTable: r["default"]}, data: function () {
                return {
                    btnLoading: !1,
                    active: 0,
                    mysqlVO: {database: "", table: ""},
                    params: {
                        type: "",
                        desc: "",
                        fieldList: [],
                        sourceInfo: {},
                        sample: "",
                        dimensionList: [],
                        metricList: [],
                        rollUp: !1
                    },
                    types: [{text: "数据库", value: "MYSQL"}, {text: "埋点", value: "BURY"}]
                }
            }, computed: {
                mappedFields: function () {
                    var t = this;
                    return this.params.fieldList ? this.params.fieldList.map(function (e) {
                        return {field: e, type: "MYSQL" === t.params.type ? "dimension" : null}
                    }) : []
                }
            }, methods: {
                clear: function () {
                    this.params = {
                        type: "",
                        desc: "",
                        fieldList: [],
                        sourceInfo: {},
                        sample: "",
                        dimensionList: [],
                        metricList: []
                    }, this.active = 0
                }, getFields: function () {
                    var t = this, e = "";
                    try {
                        "BURY" === this.params.type && (e = JSON.parse(this.params.sample))
                    } catch (s) {
                        return this.$store.dispatch("alert", {
                            type: "error",
                            content: "请检查json格式是否正确"
                        }), void (this.btnLoading = !1)
                    }
                    console.log("json", e);
                    var a = {info: "MYSQL" === this.params.type ? this.handlerMysqlInfo() : e, type: this.params.type};
                    n["c"].listFields(a).then(function (e) {
                        t.params.fieldList = e, t.btnLoading = !1, t.active++
                    }).catch(function (e) {
                        t.btnLoading = !1
                    })
                }, handlerMysqlInfo: function () {
                    return this.params.sourceInfo = {
                        user: this.params.sourceInfo.user ? this.params.sourceInfo.user : "root",
                        password: this.params.sourceInfo.password ? this.params.sourceInfo.password : "123456",
                        host: this.params.sourceInfo.host ? this.params.sourceInfo.host : "localhost:3307",
                        database: this.params.sourceInfo.database,
                        table: this.params.sourceInfo.table
                    }, this.params.sourceInfo
                }, next: function () {
                    var t = this;
                    if (0 === this.active) this.btnLoading = !0, this.getFields(); else {
                        var e = [];
                        try {
                            e = this.$refs.table.items
                        } catch (a) {
                            return void this.$store.dispatch("alert", {type: "error", content: "请检查json格式是否正确"})
                        }
                        this.params.dimensionList = e.filter(function (t) {
                            return "dimension" === t.type
                        }).map(function (t) {
                            return t.field
                        }), this.params.metricList = e.filter(function (t) {
                            return "metric" === t.type
                        }).map(function (t) {
                            return t.field
                        }), console.log(this.params), n["c"].add(this.params).then(function (e) {
                            t.$emit("add_over"), t.clear()
                        })
                    }
                }
            }
        }, c = o, l = a("2877"), u = Object(l["a"])(c, s, i, !1, null, null, null);
        e["default"] = u.exports
    }, "453f": function (t, e, a) {
    }, 4678: function (t, e, a) {
        var s = {
            "./af": "2bfb",
            "./af.js": "2bfb",
            "./ar": "8e73",
            "./ar-dz": "a356",
            "./ar-dz.js": "a356",
            "./ar-kw": "423e",
            "./ar-kw.js": "423e",
            "./ar-ly": "1cfd",
            "./ar-ly.js": "1cfd",
            "./ar-ma": "0a84",
            "./ar-ma.js": "0a84",
            "./ar-sa": "8230",
            "./ar-sa.js": "8230",
            "./ar-tn": "6d83",
            "./ar-tn.js": "6d83",
            "./ar.js": "8e73",
            "./az": "485c",
            "./az.js": "485c",
            "./be": "1fc1",
            "./be.js": "1fc1",
            "./bg": "84aa",
            "./bg.js": "84aa",
            "./bm": "a7fa",
            "./bm.js": "a7fa",
            "./bn": "9043",
            "./bn.js": "9043",
            "./bo": "d26a",
            "./bo.js": "d26a",
            "./br": "6887",
            "./br.js": "6887",
            "./bs": "2554",
            "./bs.js": "2554",
            "./ca": "d716",
            "./ca.js": "d716",
            "./cs": "3c0d",
            "./cs.js": "3c0d",
            "./cv": "03ec",
            "./cv.js": "03ec",
            "./cy": "9797",
            "./cy.js": "9797",
            "./da": "0f14",
            "./da.js": "0f14",
            "./de": "b469",
            "./de-at": "b3eb",
            "./de-at.js": "b3eb",
            "./de-ch": "bb71",
            "./de-ch.js": "bb71",
            "./de.js": "b469",
            "./dv": "598a",
            "./dv.js": "598a",
            "./el": "8d47",
            "./el.js": "8d47",
            "./en-SG": "cdab",
            "./en-SG.js": "cdab",
            "./en-au": "0e6b",
            "./en-au.js": "0e6b",
            "./en-ca": "3886",
            "./en-ca.js": "3886",
            "./en-gb": "39a6",
            "./en-gb.js": "39a6",
            "./en-ie": "e1d3",
            "./en-ie.js": "e1d3",
            "./en-il": "7333",
            "./en-il.js": "7333",
            "./en-nz": "6f50",
            "./en-nz.js": "6f50",
            "./eo": "65db",
            "./eo.js": "65db",
            "./es": "898b",
            "./es-do": "0a3c",
            "./es-do.js": "0a3c",
            "./es-us": "55c9",
            "./es-us.js": "55c9",
            "./es.js": "898b",
            "./et": "ec18",
            "./et.js": "ec18",
            "./eu": "0ff2",
            "./eu.js": "0ff2",
            "./fa": "8df4",
            "./fa.js": "8df4",
            "./fi": "81e9",
            "./fi.js": "81e9",
            "./fo": "0721",
            "./fo.js": "0721",
            "./fr": "9f26",
            "./fr-ca": "d9f8",
            "./fr-ca.js": "d9f8",
            "./fr-ch": "0e49",
            "./fr-ch.js": "0e49",
            "./fr.js": "9f26",
            "./fy": "7118",
            "./fy.js": "7118",
            "./ga": "5120",
            "./ga.js": "5120",
            "./gd": "f6b4",
            "./gd.js": "f6b4",
            "./gl": "8840",
            "./gl.js": "8840",
            "./gom-latn": "0caa",
            "./gom-latn.js": "0caa",
            "./gu": "e0c5",
            "./gu.js": "e0c5",
            "./he": "c7aa",
            "./he.js": "c7aa",
            "./hi": "dc4d",
            "./hi.js": "dc4d",
            "./hr": "4ba9",
            "./hr.js": "4ba9",
            "./hu": "5b14",
            "./hu.js": "5b14",
            "./hy-am": "d6b6",
            "./hy-am.js": "d6b6",
            "./id": "5038",
            "./id.js": "5038",
            "./is": "0558",
            "./is.js": "0558",
            "./it": "6e98",
            "./it-ch": "6f12",
            "./it-ch.js": "6f12",
            "./it.js": "6e98",
            "./ja": "079e",
            "./ja.js": "079e",
            "./jv": "b540",
            "./jv.js": "b540",
            "./ka": "201b",
            "./ka.js": "201b",
            "./kk": "6d79",
            "./kk.js": "6d79",
            "./km": "e81d",
            "./km.js": "e81d",
            "./kn": "3e92",
            "./kn.js": "3e92",
            "./ko": "22f8",
            "./ko.js": "22f8",
            "./ku": "2421",
            "./ku.js": "2421",
            "./ky": "9609",
            "./ky.js": "9609",
            "./lb": "440c",
            "./lb.js": "440c",
            "./lo": "b29d",
            "./lo.js": "b29d",
            "./lt": "26f9",
            "./lt.js": "26f9",
            "./lv": "b97c",
            "./lv.js": "b97c",
            "./me": "293c",
            "./me.js": "293c",
            "./mi": "688b",
            "./mi.js": "688b",
            "./mk": "6909",
            "./mk.js": "6909",
            "./ml": "02fb",
            "./ml.js": "02fb",
            "./mn": "958b",
            "./mn.js": "958b",
            "./mr": "39bd",
            "./mr.js": "39bd",
            "./ms": "ebe4",
            "./ms-my": "6403",
            "./ms-my.js": "6403",
            "./ms.js": "ebe4",
            "./mt": "1b45",
            "./mt.js": "1b45",
            "./my": "8689",
            "./my.js": "8689",
            "./nb": "6ce3",
            "./nb.js": "6ce3",
            "./ne": "3a39",
            "./ne.js": "3a39",
            "./nl": "facd",
            "./nl-be": "db29",
            "./nl-be.js": "db29",
            "./nl.js": "facd",
            "./nn": "b84c",
            "./nn.js": "b84c",
            "./pa-in": "f3ff",
            "./pa-in.js": "f3ff",
            "./pl": "8d57",
            "./pl.js": "8d57",
            "./pt": "f260",
            "./pt-br": "d2d4",
            "./pt-br.js": "d2d4",
            "./pt.js": "f260",
            "./ro": "972c",
            "./ro.js": "972c",
            "./ru": "957c",
            "./ru.js": "957c",
            "./sd": "6784",
            "./sd.js": "6784",
            "./se": "ffff",
            "./se.js": "ffff",
            "./si": "eda5",
            "./si.js": "eda5",
            "./sk": "7be6",
            "./sk.js": "7be6",
            "./sl": "8155",
            "./sl.js": "8155",
            "./sq": "c8f3",
            "./sq.js": "c8f3",
            "./sr": "cf1e",
            "./sr-cyrl": "13e9",
            "./sr-cyrl.js": "13e9",
            "./sr.js": "cf1e",
            "./ss": "52bd",
            "./ss.js": "52bd",
            "./sv": "5fbd",
            "./sv.js": "5fbd",
            "./sw": "74dc",
            "./sw.js": "74dc",
            "./ta": "3de5",
            "./ta.js": "3de5",
            "./te": "5cbb",
            "./te.js": "5cbb",
            "./tet": "576c",
            "./tet.js": "576c",
            "./tg": "3b1b",
            "./tg.js": "3b1b",
            "./th": "10e8",
            "./th.js": "10e8",
            "./tl-ph": "0f38",
            "./tl-ph.js": "0f38",
            "./tlh": "cf75",
            "./tlh.js": "cf75",
            "./tr": "0e81",
            "./tr.js": "0e81",
            "./tzl": "cf51",
            "./tzl.js": "cf51",
            "./tzm": "c109",
            "./tzm-latn": "b53d",
            "./tzm-latn.js": "b53d",
            "./tzm.js": "c109",
            "./ug-cn": "6117",
            "./ug-cn.js": "6117",
            "./uk": "ada2",
            "./uk.js": "ada2",
            "./ur": "5294",
            "./ur.js": "5294",
            "./uz": "2e8c",
            "./uz-latn": "010e",
            "./uz-latn.js": "010e",
            "./uz.js": "2e8c",
            "./vi": "2921",
            "./vi.js": "2921",
            "./x-pseudo": "fd7e",
            "./x-pseudo.js": "fd7e",
            "./yo": "7f33",
            "./yo.js": "7f33",
            "./zh-cn": "5c3a",
            "./zh-cn.js": "5c3a",
            "./zh-hk": "49ab",
            "./zh-hk.js": "49ab",
            "./zh-tw": "90ea",
            "./zh-tw.js": "90ea"
        };

        function i(t) {
            var e = r(t);
            return a(e)
        }

        function r(t) {
            var e = s[t];
            if (!(e + 1)) {
                var a = new Error("Cannot find module '" + t + "'");
                throw a.code = "MODULE_NOT_FOUND", a
            }
            return e
        }

        i.keys = function () {
            return Object.keys(s)
        }, i.resolve = r, t.exports = i, i.id = "4678"
    }, 4999: function (t, e, a) {
        var s = {
            "./en/Common.json": "0a2d",
            "./en/Core/Footer.json": "173d",
            "./en/Core/Toolbar.json": "f26b",
            "./en/Home.json": "5cc6"
        };

        function i(t) {
            var e = r(t);
            return a(e)
        }

        function r(t) {
            var e = s[t];
            if (!(e + 1)) {
                var a = new Error("Cannot find module '" + t + "'");
                throw a.code = "MODULE_NOT_FOUND", a
            }
            return e
        }

        i.keys = function () {
            return Object.keys(s)
        }, i.resolve = r, t.exports = i, i.id = "4999"
    }, "4d79": function (t, e, a) {
        "use strict";
        var s = a("803b"), i = a.n(s);
        i.a
    }, "56d7": function (t, e, a) {
        "use strict";
        a.r(e);
        a("cadf"), a("551c"), a("f751"), a("097d");
        var s = a("2b0e"), i = (a("a481"), a("ac6a"), a("8103")), r = a.n(i), n = a("bba4"), o = a.n(n), c = a("ffe0");
        c.keys().forEach(function (t) {
            var e = c(t), a = r()(o()(t.replace(/^\.\//, "").replace(/\.\w+$/, "")));
            s["default"].component(a, e.default || e)
        });
        var l = a("bc3a"), u = a.n(l);
        s["default"].prototype.$http = u.a;
        var d = a("ce5b"), f = a.n(d), m = {
            primary: "#4caf50",
            secondary: "#4caf50",
            tertiary: "#495057",
            accent: "#82B1FF",
            error: "#f55a4e",
            info: "#00d3ee",
            success: "#5cb860",
            warning: "#ffa21a"
        };
        a("bf40"), a("5363");
        s["default"].use(f.a, {iconfont: "mdi", theme: m});
        var p = a("31bd"), h = function () {
                var t = this, e = t.$createElement, a = t._self._c || e;
                return a("v-app", [a("core-toolbar"), a("core-drawer"), a("core-view")], 1)
            }, v = [], b = (a("5c0b"), a("2877")), g = {}, y = Object(b["a"])(g, h, v, !1, null, null, null), x = y.exports,
            w = a("a925"), j = (a("28a5"), a("5d73")), k = a.n(j), _ = a("4999"), D = {}, T = !0, I = !1, C = void 0;
        try {
            for (var $, S = function () {
                var t = $.value;
                if ("./index.js" === t) return "continue";
                var e = t.replace(/(\.\/|\.json$)/g, "").split("/");
                e.reduce(function (a, s, i) {
                    return a[s] ? a[s] : (a[s] = i + 1 === e.length ? _(t) : {}, a[s])
                }, D)
            }, O = k()(_.keys()); !(T = ($ = O.next()).done); T = !0) S()
        } catch (Y) {
            I = !0, C = Y
        } finally {
            try {
                T || null == O.return || O.return()
            } finally {
                if (I) throw C
            }
        }
        var L = D;
        s["default"].use(w["a"]);
        var M = new w["a"]({locale: "en", fallbackLocale: "en", messages: L}), E = M, q = (a("7f7f"), a("8c4f")),
            N = a("0a89"), A = a.n(N), z = [{path: "/user-profile", name: "User Profile", view: "UserProfile"}, {
                path: "/table-list",
                name: "Table List",
                view: "TableList"
            }, {path: "/typography", view: "Typography"}, {path: "/icons", view: "Icons"}, {
                path: "/maps",
                view: "Maps"
            }, {path: "/notifications", view: "Notifications"}, {path: "/test", view: "ChartsTest"}, {
                path: "/datasource",
                view: "Datasource"
            }, {path: "/metricvariable", view: "MetricVariable"}, {path: "/dashboardv2/:id", view: "DashboardV2"}];

        function F(t, e, s) {
            return {
                name: s || e, path: t, component: function (t) {
                    return a("1a5d")("./".concat(e, ".vue")).then(t)
                }
            }
        }

        s["default"].use(q["a"]);
        var U = new q["a"]({
            mode: "history", routes: z.map(function (t) {
                return F(t.path, t.view, t.name)
            }).concat([{path: "*", redirect: "/dashboardv2/1"}]), scrollBehavior: function (t, e, a) {
                return a || (t.hash ? {selector: t.hash} : {x: 0, y: 0})
            }
        });
        s["default"].use(A.a);
        var V = U, P = a("4360");
        Object(p["sync"])(P["a"], V), s["default"].config.productionTip = !1, new s["default"]({
            i18n: E,
            router: V,
            store: P["a"],
            render: function (t) {
                return t(x)
            }
        }).$mount("#app")
    }, "5c0b": function (t, e, a) {
        "use strict";
        var s = a("5e27"), i = a.n(s);
        i.a
    }, "5cc6": function (t) {
        t.exports = {
            title: "Vuetify Alpha",
            footer: "2018 Vuetify LLC",
            drawerItems: ["Inspire"],
            needHelp: "Need help?"
        }
    }, "5e27": function (t, e, a) {
    }, 6096: function (t, e, a) {
        "use strict";
        a.r(e);
        var s = function (t) {
            return function (e, a) {
                return e[t] = a
            }
        }, i = function (t) {
            return function (e) {
                return e[t] = !e[t]
            }
        };
        e["default"] = {setDrawer: s("drawer"), setImage: s("image"), setColor: s("color"), toggleDrawer: i("drawer")}
    }, "68c7": function (t, e, a) {
        "use strict";
        a.r(e);
        var s = function () {
            var t = this, e = t.$createElement, a = t._self._c || e;
            return a("form", {staticStyle: {padding: "24px"}}, [a("v-select", {
                attrs: {
                    items: t.types,
                    label: "图表类型",
                    "item-text": "text",
                    "item-value": "value",
                    disabled: t.isUpdating
                }, model: {
                    value: t.params.type, callback: function (e) {
                        t.$set(t.params, "type", e)
                    }, expression: "params.type"
                }
            }), a("v-text-field", {
                attrs: {label: "名称", required: ""},
                model: {
                    value: t.params.name, callback: function (e) {
                        t.$set(t.params, "name", e)
                    }, expression: "params.name"
                }
            }), a("v-text-field", {
                attrs: {label: "描述", required: ""},
                model: {
                    value: t.params.desc, callback: function (e) {
                        t.$set(t.params, "desc", e)
                    }, expression: "params.desc"
                }
            }), "funnel" !== this.params.type ? a("v-select", {
                attrs: {
                    items: t.datasources,
                    label: "数据源",
                    "item-text": "desc",
                    "item-value": "id",
                    disabled: t.isUpdating
                }, model: {
                    value: t.params.datasourceId, callback: function (e) {
                        t.$set(t.params, "datasourceId", e)
                    }, expression: "params.datasourceId"
                }
            }) : a("v-select", {
                attrs: {
                    items: t.datasources,
                    label: "数据源（多选）",
                    "item-text": "desc",
                    "item-value": "id",
                    multiple: "",
                    chips: ""
                }, model: {
                    value: t.params.config.datasourceIds, callback: function (e) {
                        t.$set(t.params.config, "datasourceIds", e)
                    }, expression: "params.config.datasourceIds"
                }
            }), t._l(t.params.aggregations, function (e, s) {
                return t.allowMetric ? a("v-container", {key: "agg" + s}, [a("v-layout", [a("v-flex", {
                    attrs: {
                        md4: "",
                        lg4: "",
                        xs12: ""
                    }
                }, [a("v-select", {
                    attrs: {items: t.choosedDatasource.metricList, label: "指标字段"},
                    model: {
                        value: e.metric, callback: function (a) {
                            t.$set(e, "metric", a)
                        }, expression: "item.metric"
                    }
                })], 1), a("v-flex", {
                    attrs: {
                        md4: "",
                        lg4: "",
                        xs12: ""
                    }
                }, [a("v-select", {
                    attrs: {
                        items: t.getAggregationsTypeOption(e.metric),
                        label: "聚合类型",
                        "item-text": "k",
                        "item-value": "v"
                    }, model: {
                        value: e.metricAggregationType, callback: function (a) {
                            t.$set(e, "metricAggregationType", a)
                        }, expression: "item.metricAggregationType"
                    }
                })], 1), a("v-flex", {attrs: {md4: "", lg4: "", xs12: ""}}, [a("v-text-field", {
                    attrs: {
                        label: "别名",
                        required: ""
                    }, model: {
                        value: e.alias, callback: function (a) {
                            t.$set(e, "alias", a)
                        }, expression: "item.alias"
                    }
                })], 1)], 1)], 1) : t._e()
            }), t._l(t.params.dimensions, function (e, s) {
                return t.allowDimension ? a("v-container", {key: "dimen" + s}, [a("v-layout", [a("v-flex", {
                    attrs: {
                        md5: "",
                        lg5: "",
                        xs12: ""
                    }
                }, [a("v-select", {
                    attrs: {items: t.choosedDatasource.dimensionList, label: "维度字段"},
                    model: {
                        value: e.dimensionField, callback: function (a) {
                            t.$set(e, "dimensionField", a)
                        }, expression: "item.dimensionField"
                    }
                })], 1), a("v-flex", {attrs: {md4: "", lg4: "", xs12: ""}}, [a("v-text-field", {
                    attrs: {
                        label: "别名",
                        required: ""
                    }, model: {
                        value: e.alias, callback: function (a) {
                            t.$set(e, "alias", a)
                        }, expression: "item.alias"
                    }
                })], 1), t.singleDimension ? a("v-flex", {
                    attrs: {
                        md3: "",
                        lg3: "",
                        xs12: ""
                    }
                }, [a("v-text-field", {
                    attrs: {label: "阈值", required: ""},
                    model: {
                        value: t.params.threshold, callback: function (e) {
                            t.$set(t.params, "threshold", e)
                        }, expression: "params.threshold"
                    }
                })], 1) : a("v-flex", {
                    attrs: {
                        md3: "",
                        lg3: "",
                        xs12: ""
                    }
                }, [t.params.dimensions.length === s + 1 ? a("v-btn", {
                    attrs: {flat: "", color: "success"},
                    on: {click: t.putNewEmptyDimension}
                }, [t._v("新增维度\n                ")]) : t._e(), a("v-btn", {
                    attrs: {flat: "", color: "error"},
                    on: {
                        click: function (e) {
                            return t.params.dimensions.splice(s, 1)
                        }
                    }
                }, [t._v("删除此项")])], 1)], 1)], 1) : t._e()
            }), "stats" === this.params.type ? a("v-container", [a("v-layout", [a("v-flex", {
                attrs: {
                    md6: "",
                    lg6: "",
                    xs12: ""
                }
            }, [a("v-text-field", {
                attrs: {label: "显示前缀"},
                model: {
                    value: t.params.config.prefix, callback: function (e) {
                        t.$set(t.params.config, "prefix", e)
                    }, expression: "params.config.prefix"
                }
            })], 1), a("v-flex", {attrs: {md6: "", lg6: "", xs12: ""}}, [a("v-text-field", {
                attrs: {
                    label: "显示后缀",
                    required: ""
                }, model: {
                    value: t.params.config.suffix, callback: function (e) {
                        t.$set(t.params.config, "suffix", e)
                    }, expression: "params.config.suffix"
                }
            })], 1)], 1)], 1) : t._e(), a("v-btn", {
                attrs: {color: "success"},
                on: {click: t.submit}
            }, [t._v("提交")]), a("v-btn", {attrs: {color: "error"}, on: {click: t.clear}}, [t._v("重置")])], 2)
        }, i = [], r = (a("6762"), a("2fdb"), a("7514"), a("f891")), n = {
            props: ["dashboardId", "isUpdating", "targetChart"],
            watch: {
                "params.datasourceId": {
                    handler: function (t, e) {
                        0 !== this.params.datasourceId && (this.params.aggregations.length < 1 && this.putNewEmptyAggregation(), this.params.dimensions.length < 1 && this.putNewEmptyDimension())
                    }, deep: !0
                }, targetChart: function (t, e) {
                    this.params = this.targetChart
                }
            },
            computed: {
                choosedDatasource: function () {
                    var t = this, e = this.datasources.find(function (e) {
                        return e.id === t.params.datasourceId
                    });
                    return e || (e = {metricList: [], dimensionList: []}), e
                }, allowMetric: function () {
                    return "funnel" !== this.params.type
                }, allowDimension: function () {
                    return !["funnel", "stats", "line"].includes(this.params.type)
                }, singleDimension: function () {
                    return ["pie"].includes(this.params.type)
                }, singleMetric: function () {
                }
            },
            data: function () {
                return {
                    params: {
                        type: "",
                        desc: "",
                        name: "",
                        datasourceId: 0,
                        dashboardId: "",
                        aggregations: [],
                        dimensions: [],
                        filters: [],
                        config: {},
                        threshold: 4
                    },
                    types: [{text: "折线图", value: "line"}, {text: "状态图", value: "stats"}, {
                        text: "饼图",
                        value: "pie"
                    }, {text: "漏斗图", value: "funnel"}],
                    datasources: [],
                    choosedDimensions: [],
                    newChartId: -1
                }
            },
            methods: {
                getAggregationsTypeOption: function (t) {
                    return this.choosedDatasource && this.choosedDatasource.metricList.length < 1 ? [] : "count" === t ? [{
                        k: "计数:COUNT()",
                        v: "COUNT"
                    }] : [{k: "总和", v: "SUM"}, {k: "最大值", v: "MAX"}, {k: "最小值", v: "MIN"}, {
                        k: "去重计数:COUNT(DISTINCT)",
                        v: "DISTINCT"
                    }, {k: "计数:COUNT()", v: "COUNT"}]
                }, putNewEmptyAggregation: function () {
                    this.params.aggregations.push({metric: "", alias: "", metricAggregationType: ""})
                }, putNewEmptyDimension: function () {
                    this.params.dimensions.push({dimensionField: "", alias: ""})
                }, clear: function () {
                    this.params = {
                        type: "",
                        desc: "",
                        name: "",
                        datasourceId: 1,
                        dashboardId: 0,
                        aggregations: [],
                        dimensions: [],
                        filters: [],
                        config: {}
                    }
                }, submit: function () {
                    var t = this;
                    this.params.aggregations = this.params.aggregations.filter(function (t) {
                        return t.metric && "" !== t.metric
                    }), this.params.dimensions = this.params.dimensions.filter(function (t) {
                        return t.dimensionField && "" !== t.dimensionField
                    }), this.params.dashboardId = this.dashboardId, console.log(this.params), this.isUpdating ? (console.log("想要更新的图表id为", this.params.id), r["a"].update(this.params.id, this.params).then(function (e) {
                        t.$store.dispatch("alert", {
                            type: "success",
                            content: "更新成功"
                        }), t.$emit("chart_submit", t.params.id)
                    })) : ("pie" !== this.params.type && (this.params.threshold = 200), "funnel" === this.params.type && (this.params.aggregations = [{
                        alias: "数量",
                        metric: "count",
                        metricAggregationType: "COUNT"
                    }], this.params.dimensions = [{
                        alias: "名称",
                        dimensionField: "name"
                    }]), r["a"].add(this.params).then(function (e) {
                        t.newChartId = e, console.log("提交成功,图表id=" + t.newChartId), t.$emit("chart_submit", t.newChartId), t.clear()
                    }))
                }, getDatasources: function () {
                    var t = this;
                    r["c"].list().then(function (e) {
                        t.datasources = e
                    })
                }
            },
            mounted: function () {
                this.getDatasources()
            }
        }, o = n, c = a("2877"), l = Object(c["a"])(o, s, i, !1, null, null, null);
        e["default"] = l.exports
    }, "6b35": function (t, e, a) {
    }, "703d": function (t, e, a) {
    }, "803b": function (t, e, a) {
    }, 9306: function (t, e, a) {
        "use strict";
        a.r(e);
        var s = function () {
            var t = this, e = t.$createElement, a = t._self._c || e;
            return a("v-content", [a("v-snackbar", {
                attrs: {
                    color: t.$store.state.type,
                    value: t.$store.state.showAlert,
                    top: "",
                    right: ""
                }
            }, [t._v("\n    " + t._s(t.$store.state.content) + "\n    "), a("v-btn", {
                attrs: {flat: ""},
                on: {
                    click: function (e) {
                        return t.$store.commit("cancelAlert")
                    }
                }
            }, [t._v("\n      好的\n    ")])], 1), a("div", {attrs: {id: "core-view"}}, [a("v-fade-transition", {attrs: {mode: "out-in"}}, [a("router-view")], 1)], 1)], 1)
        }, i = [], r = {
            metaInfo: function () {
                return {title: "hwakeye by huahuasmaster"}
            }
        }, n = r, o = (a("ee4f"), a("2877")), c = Object(o["a"])(n, s, i, !1, null, null, null);
        e["default"] = c.exports
    }, a38c: function (t, e, a) {
        "use strict";
        a.r(e);
        var s = function () {
            var t = this, e = t.$createElement, a = t._self._c || e;
            return a("v-menu", {
                ref: "menu",
                attrs: {
                    "close-on-content-click": !1,
                    "nudge-right": 40,
                    "return-value": t.wholeDate,
                    lazy: "",
                    transition: "scale-transition",
                    "offset-y": "",
                    "full-width": "",
                    "min-width": "310px"
                },
                on: {
                    "update:returnValue": function (e) {
                        t.wholeDate = e
                    }, "update:return-value": function (e) {
                        t.wholeDate = e
                    }
                },
                scopedSlots: t._u([{
                    key: "activator", fn: function (e) {
                        var s = e.on;
                        return [a("v-text-field", t._g({
                            ref: "text",
                            staticStyle: {color: "white"},
                            attrs: {dark: "", prefix: t.label},
                            model: {
                                value: t.wholeDate, callback: function (e) {
                                    t.wholeDate = e
                                }, expression: "wholeDate"
                            }
                        }, s))]
                    }
                }]),
                model: {
                    value: t.menu, callback: function (e) {
                        t.menu = e
                    }, expression: "menu"
                }
            }, [t.showTime ? t._e() : a("v-date-picker", {
                attrs: {dark: ""},
                on: {input: t.confirm},
                model: {
                    value: t.date, callback: function (e) {
                        t.date = e
                    }, expression: "date"
                }
            }, [a("v-spacer"), a("v-btn", {
                attrs: {flat: "", color: "primary"},
                on: {click: t.cancel}
            }, [t._v("Cancel")]), a("v-btn", {
                attrs: {flat: "", color: "primary"}, on: {
                    click: function (e) {
                        return t.confirm()
                    }
                }
            }, [t._v("OK")])], 1), t.showTime ? a("v-time-picker", {
                attrs: {dark: ""},
                model: {
                    value: t.time, callback: function (e) {
                        t.time = e
                    }, expression: "time"
                }
            }, [a("v-spacer"), a("v-btn", {
                attrs: {flat: "", color: "primary"},
                on: {click: t.cancel}
            }, [t._v("Cancel")]), a("v-btn", {
                attrs: {flat: "", color: "primary"}, on: {
                    click: function (e) {
                        return t.confirm()
                    }
                }
            }, [t._v("OK")])], 1) : t._e()], 1)
        }, i = [], r = a("c1df"), n = {
            props: ["label", "defaultDate"], computed: {}, watch: {}, data: function () {
                return {showTime: !1, menu: !1, wholeDate: "", date: "", time: ""}
            }, methods: {
                confirm: function () {
                    this.showTime ? (this.wholeDate = this.date + " " + this.time + ":00", this.$refs.menu.save(this.date + " " + this.time), this.$emit("pick_datetime", new Date(this.wholeDate).valueOf()), this.menu = !1, this.showTime = !1) : this.showTime = !0
                }, cancel: function () {
                    this.menu = !1, this.showTime = !1
                }, trans: function () {
                    this.wholeDate = r(this.defaultDate).format("YYYY-MM-DD HH:mm"), this.date = r(this.defaultDate).format("YYYY-MM-DD"), this.time = r(this.defaultDate).format("HH:mm")
                }
            }, mounted: function () {
                this.trans()
            }
        }, o = n, c = a("2877"), l = Object(c["a"])(o, s, i, !1, null, null, null);
        e["default"] = l.exports
    }, ad75: function (t, e, a) {
        "use strict";
        var s = a("6b35"), i = a.n(s);
        i.a
    }, c653: function (t, e, a) {
        var s = {"./app/mutations.js": "6096", "./app/state.js": "2609", "./index.js": "2a74"};

        function i(t) {
            var e = r(t);
            return a(e)
        }

        function r(t) {
            var e = s[t];
            if (!(e + 1)) {
                var a = new Error("Cannot find module '" + t + "'");
                throw a.code = "MODULE_NOT_FOUND", a
            }
            return e
        }

        i.keys = function () {
            return Object.keys(s)
        }, i.resolve = r, t.exports = i, i.id = "c653"
    }, c6cc: function (t, e, a) {
        "use strict";
        a.r(e);
        var s = function () {
            var t = this, e = t.$createElement, a = t._self._c || e;
            return a("div", {staticClass: "v-offset", class: t.classes, style: t.styles}, [t._t("default")], 2)
        }, i = [], r = (a("c5f6"), {
            props: {
                fullWidth: {type: Boolean, default: !1},
                offset: {type: [Number, String], default: 0}
            }, computed: {
                classes: function () {
                    return {"v-offset--full-width": this.fullWidth}
                }, styles: function () {
                    return {top: "-".concat(this.offset, "px"), marginBottom: "-".concat(this.offset, "px")}
                }
            }
        }), n = r, o = (a("1196"), a("2877")), c = Object(o["a"])(n, s, i, !1, null, null, null);
        e["default"] = c.exports
    }, cb2c: function (t, e, a) {
        "use strict";
        var s = a("4025"), i = a.n(s);
        i.a
    }, d23b: function (t, e, a) {
        "use strict";
        a.r(e);
        var s = function () {
            var t = this, e = t.$createElement, a = t._self._c || e;
            return a("v-toolbar", {
                staticStyle: {background: "#eee"},
                attrs: {id: "core-toolbar", flat: "", prominent: ""}
            }, [a("div", {staticClass: "v-toolbar-title"}, [a("v-toolbar-title", {staticClass: "tertiary--text font-weight-light"}, [t.responsive ? a("v-btn", {
                staticClass: "default v-btn--simple",
                attrs: {dark: "", icon: ""},
                on: {
                    click: function (e) {
                        return e.stopPropagation(), t.onClickBtn(e)
                    }
                }
            }, [a("v-icon", [t._v("mdi-view-list")])], 1) : t._e(), t._v("\n      " + t._s(t.title) + "\n    ")], 1)], 1), a("v-spacer"), a("v-toolbar-items", [a("v-flex", {
                attrs: {
                    "align-center": "",
                    layout: "",
                    "py-2": ""
                }
            }, [t.responsiveInput ? a("v-text-field", {
                staticClass: "mr-4 mt-2 purple-input",
                attrs: {label: "Search...", "hide-details": "", color: "purple"}
            }) : t._e(), a("router-link", {
                directives: [{name: "ripple", rawName: "v-ripple"}],
                staticClass: "toolbar-items",
                attrs: {to: "/"}
            }, [a("v-icon", {attrs: {color: "tertiary"}}, [t._v("mdi-view-dashboard")])], 1), a("v-menu", {
                attrs: {
                    bottom: "",
                    left: "",
                    "content-class": "dropdown-menu",
                    "offset-y": "",
                    transition: "slide-y-transition"
                }
            }, [a("router-link", {
                directives: [{name: "ripple", rawName: "v-ripple"}],
                staticClass: "toolbar-items",
                attrs: {slot: "activator", to: "/notifications"},
                slot: "activator"
            }, [a("v-badge", {
                attrs: {
                    color: "error",
                    overlap: ""
                }
            }, [a("template", {slot: "badge"}, [t._v("\n              " + t._s(t.notifications.length) + "\n            ")]), a("v-icon", {attrs: {color: "tertiary"}}, [t._v("mdi-bell")])], 2)], 1), a("v-card", [a("v-list", {attrs: {dense: ""}}, t._l(t.notifications, function (e) {
                return a("v-list-tile", {
                    key: e,
                    on: {click: t.onClick}
                }, [a("v-list-tile-title", {domProps: {textContent: t._s(e)}})], 1)
            }), 1)], 1)], 1), a("router-link", {
                directives: [{name: "ripple", rawName: "v-ripple"}],
                staticClass: "toolbar-items",
                attrs: {to: "/user-profile"}
            }, [a("v-icon", {attrs: {color: "tertiary"}}, [t._v("mdi-account")])], 1)], 1)], 1)], 1)
        }, i = [], r = a("cebc"), n = (a("7f7f"), a("2f62")), o = {
            data: function () {
                return {
                    notifications: ["Mike, John responded to your email", "You have 5 new tasks", "You're now a friend with Andrew", "Another Notification", "Another One"],
                    title: null,
                    responsive: !1,
                    responsiveInput: !1
                }
            },
            watch: {
                $route: function (t) {
                    this.title = t.name
                }
            },
            mounted: function () {
                this.onResponsiveInverted(), window.addEventListener("resize", this.onResponsiveInverted)
            },
            beforeDestroy: function () {
                window.removeEventListener("resize", this.onResponsiveInverted)
            },
            methods: Object(r["a"])({}, Object(n["b"])("app", ["setDrawer", "toggleDrawer"]), {
                onClickBtn: function () {
                    this.setDrawer(!this.$store.state.app.drawer)
                }, onClick: function () {
                }, onResponsiveInverted: function () {
                    window.innerWidth < 991 ? (this.responsive = !0, this.responsiveInput = !1) : (this.responsive = !1, this.responsiveInput = !0)
                }
            })
        }, c = o, l = (a("25f5"), a("2877")), u = Object(l["a"])(c, s, i, !1, null, null, null);
        e["default"] = u.exports
    }, e3a9: function (t, e, a) {
        "use strict";
        a.r(e);
        var s = function () {
            var t = this, e = t.$createElement, a = t._self._c || e;
            return a("v-card", t._g(t._b({style: t.styles}, "v-card", t.$attrs, !1), t.$listeners), [t.hasOffset ? a("helper-offset", {
                attrs: {
                    inline: t.inline,
                    "full-width": t.fullWidth,
                    offset: t.offset
                }
            }, [t.$slots.offset ? t._t("offset") : a("v-card", {
                staticClass: "v-card--material__header",
                class: "elevation-" + t.elevation,
                attrs: {color: t.color, dark: ""}
            }, [t.title || t.text ? a("span", [a("h4", {
                staticClass: "title font-weight-light mb-2",
                domProps: {textContent: t._s(t.title)}
            }), a("p", {
                staticClass: "category font-weight-thin",
                domProps: {textContent: t._s(t.text)}
            })]) : t._t("header")], 2)], 2) : t._e(), a("v-card-text", [t._t("default")], 2), t.$slots.actions ? a("v-divider", {staticClass: "mx-3"}) : t._e(), t.$slots.actions ? a("v-card-actions", [t._t("actions")], 2) : t._e()], 1)
        }, i = [], r = (a("c5f6"), {
            inheritAttrs: !1,
            props: {
                color: {type: String, default: "secondary"},
                elevation: {type: [Number, String], default: 10},
                inline: {type: Boolean, default: !1},
                fullWidth: {type: Boolean, default: !1},
                offset: {type: [Number, String], default: 24},
                title: {type: String, default: void 0},
                text: {type: String, default: void 0}
            },
            computed: {
                hasOffset: function () {
                    return this.$slots.header || this.$slots.offset || this.title || this.text
                }, styles: function () {
                    return this.hasOffset ? {
                        marginBottom: "".concat(this.offset, "px"),
                        marginTop: "".concat(2 * this.offset, "px")
                    } : null
                }
            }
        }), n = r, o = (a("cb2c"), a("2877")), c = Object(o["a"])(n, s, i, !1, null, null, null);
        e["default"] = c.exports
    }, ee4f: function (t, e, a) {
        "use strict";
        var s = a("703d"), i = a.n(s);
        i.a
    }, f26b: function (t) {
        t.exports = {title: "Title"}
    }, f891: function (t, e, a) {
        "use strict";
        a.d(e, "a", function () {
            return d
        }), a.d(e, "b", function () {
            return f
        }), a.d(e, "c", function () {
            return m
        });
        var s = a("795b"), i = a.n(s), r = a("f499"), n = a.n(r), o = a("bc3a"), c = a.n(o), l = a("4360"),
            u = c.a.create({});
        u.interceptors.response.use(function (t) {
            if (!0 === t.data.result) return t.data.data;
            console.log("指明的错误"), l["a"].dispatch("alert", {type: "error", content: t.data.errorMsg})
        }, function (t) {
            console.log("error"), console.log(t), console.log(n()(t));
            var e = JSON.parse(n()(t)).response.status, a = e.message,
                s = 404 === JSON.parse(n()(t)).response.status ? "访问的资源不存在" : a && "No message available" === a ? "网络出错，请重试" : "服务器开小差了";
            return l["a"].dispatch("alert", {type: "error", content: s}), i.a.reject(t)
        });
        var d = {
            getViewByChart: function (t, e) {
                return u.post("/hawkeye/api/charts/".concat(t, "/data"), e, {})
            }, add: function (t) {
                return u.post("/hawkeye/api/charts/", t, {})
            }, update: function (t, e) {
                return u.put("/hawkeye/api/charts/".concat(t), e, {})
            }
        }, f = {
            listChartsByDashboardId: function (t) {
                return u.get("/hawkeye/api/dashboards/".concat(t, "/charts"))
            }, get: function (t) {
                return u.get("/hawkeye/api/dashboards/".concat(t))
            }, updateLayout: function (t, e) {
                return u.put("/hawkeye/api/dashboards/".concat(t, "/layout"), e, {})
            }, delete: function (t, e) {
                return u.delete("/hawkeye/api/dashboards/".concat(t, "/charts/").concat(e))
            }
        }, m = {
            list: function () {
                return u.get("/hawkeye/api/datasources")
            }, listFields: function (t) {
                return u.get("/hawkeye/api/datasources/fields", {params: t})
            }, add: function (t) {
                return u.post("/hawkeye/api/datasources/", t, {})
            }, updateEnable: function (t, e) {
                return u.put("/hawkeye/api/datasources/".concat(t, "/enabled"), {enable: e}, {})
            }, updateDesc: function (t, e) {
                return u.put("/hawkeye/api/datasources/".concat(dashboardId, "/desc"), e, {})
            }
        }
    }, ff57: function (t, e, a) {
        "use strict";
        var s = a("2c37"), i = a.n(s);
        i.a
    }, ffe0: function (t, e, a) {
        var s = {
            "./core/Drawer.vue": "41c0",
            "./core/Toolbar.vue": "d23b",
            "./core/View.vue": "9306",
            "./form/ChartForm.vue": "68c7",
            "./form/DatasourceForm.vue": "4394",
            "./helper/Offset.vue": "c6cc",
            "./material/Card.vue": "e3a9",
            "./material/MyDatetimePicker.vue": "a38c",
            "./material/StatsCard.vue": "42e7",
            "./material/VChartBaseCard.vue": "20ee",
            "./material/VariablePanel.vue": "1185",
            "./material/VariableTable.vue": "4093"
        };

        function i(t) {
            var e = r(t);
            return a(e)
        }

        function r(t) {
            var e = s[t];
            if (!(e + 1)) {
                var a = new Error("Cannot find module '" + t + "'");
                throw a.code = "MODULE_NOT_FOUND", a
            }
            return e
        }

        i.keys = function () {
            return Object.keys(s)
        }, i.resolve = r, t.exports = i, i.id = "ffe0"
    }
});