'use strict';

var React = require('react');
var ReactDom = require('react-dom');

class ValuesDialog extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            message: '',
            array: null
        }
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChangeMessage = this.handleChangeMessage.bind(this);
        this.handleChangeArray = this.handleChangeArray.bind(this);
    };

    handleChangeArray(data) {
        this.setState({
            message: data
        })
    }

    handleChangeMessage(a) {
        this.setState({
            array: a
        })
    }

    handleSubmit(event) {
        event.preventDefault();
        var values = [];
        var i = 0;
        for (var k = 0; k < (this.props.rows); k++) {
            values[k] = [];
            for (var j = 0; j <= this.props.cols; j++, i++) {
                values[k][j] = this.refs["x" + i].value.trim();
                this.refs["x" + i].value = '';
            }
        }
        values = JSON.stringify({array: values});
        var that = this;
        var sendValuesArray = function (values) {
            fetch('/result', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: values
            })
                .then(res => res.json())
                .then(
                    function (data) {
                        console.log(data);
                        that.setState({
                            array: data["array"]
                        });
                        that.setState({
                            message: data["message"]
                        })
                    }
                )
        }(values);
        window.location = "#";
    };

    render() {
        var inputs = [];
        var i = 0;
        var flag = 0;
        var classname = "field";
        for (var k = 0; k < (this.props.rows); k++, i++) {
            if (k == (this.props.rows - 1)) {
                classname = "func"
            }
            for (var j = 0; j < this.props.cols - 1; j++, i++) {
                if (k == (this.props.rows - 1) && flag == 0)
                    inputs[i] = function () {
                        flag = 1;
                        var mes = "Целевая функция";
                        return (
                            <text>
                                <br/>
                                <b>{mes}</b>
                                <br/>
                                <input type="text" className={classname} ref={"x" + (i)} placeholder={"x" + (j + 1)}
                                       key={"x" + (i)}/>
                                +
                            </text>
                        )
                    }();
                else
                    inputs[i] = function () {
                        return (
                            <text>
                                <input type="text" ref={"x" + (i)} className={classname} placeholder={"x" + (j + 1)}
                                       key={"x" + (i)}/>
                                +
                            </text>
                        )
                    }();

            }
            inputs[i] = function () {
                return (
                    <input type="text" className={classname} ref={"x" + (i)} placeholder={"x" + (j + 1)}
                           key={"x" + (i)}/>
                )
            }();
            i++;
            var sign, classname;
            if (k == this.props.rows - 1) {
                sign = "="
                classname = "func"
            } else {
                sign = "<";
                classname = "field";
            }
            inputs[i] = function () {
                return (
                    <text>
                        {sign}
                        <input type="text" className={classname} ref={"x" + (i)} placeholder={"srct" + (k + 1)}
                               key={"srct" + (k + 1)}/><br/>
                    </text>
                )
            }();
        }

        var dialogId = "setValues";

        return (
            <div>
                <div id={dialogId} className="modalDialog">
                    <div>
                        <a href="#" title="Close" className="close">X</a>

                        <h2>Задайте уравнения и ограничения</h2>
                        <h6>*(если переменной нет, задайте 0)</h6>
                        <form action="result" method="get">
                            <div className="inputs-array">{inputs}</div>
                            <button onClick={this.handleSubmit.bind(this)}>NEXT</button>
                        </form>
                    </div>
                </div>
                <div>
                    <b>{this.state.message}</b>
                    <br/>
                    <br/>
                    {
                        function () {
                            if (this.state.array != null) {
                                return (
                                    <div className="table-center">
                                        <table>
                                            <tbody>
                                            <tr class="simple">
                                                {
                                                    function () {
                                                        if (this.state.array == null) return;
                                                        var header = [];
                                                        for (var j = 0; j < (this.state.array[0].length); j++) {
                                                            if (j == ((this.state.array[0].length) - 1)) {
                                                                header[j] = <th>Решение</th>
                                                            } else if (j >= ((this.state.array[0].length - 1) / 2)) {
                                                                header[j] =
                                                                    <th>{"s" + (j - (this.state.array[0].length-1)/2 + 1)}</th>
                                                            } else {
                                                                header[j] = <th>{"x" + (j + 1)}</th>
                                                            }
                                                        }
                                                        return header;
                                                    }.bind(this)()
                                                }
                                            </tr>
                                            {
                                                function () {
                                                    if (this.state.array == null) return;
                                                    var values = [];
                                                    for (var i = 0; i < this.state.array.length; i++) {
                                                        values[i] = [];
                                                        for (var j = 0; j < this.state.array[0].length; j++) {
                                                            values[i][j] =
                                                                <td>{Number((this.state.array[i][j]).toFixed(2))}</td>
                                                        }
                                                        values[i] = <tr class="simple">{values[i]}</tr>
                                                    }
                                                    return values;
                                                }.bind(this)()
                                            }
                                            </tbody>
                                        </table>
                                    </div>

                                )
                            }
                        }.bind(this)()
                    }
                </div>
            </div>
        );
    };
}

class App extends React.Component {
    constructor() {
        super();
        this.state = {
            cols: '2',
            rows: '3'
        };
        this.handleChangeCols = this.handleChangeCols.bind(this);
        this.handleChangeRows = this.handleChangeRows.bind(this);
        this.onButtonClick = this.onButtonClick.bind(this);
    }

    handleChangeCols(event) {
        this.setState({
            cols: event.target.value
        });
    }

    handleChangeRows(event) {
        this.setState({
            rows: event.target.value
        });
    }

    onButtonClick(event) {
        return (
            <ValuesDialog cols={this.state.cols} rows={this.state.rows}/>
        );
    }

    render() {
        //ограничений больше на 1 для задания целевой функции
        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <span className="instruction"> Количество переменных  </span>
                    <select name="cols" className="input styled-select blue semi-square" value={this.state.cols}
                            onChange={this.handleChangeCols}>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                    </select>
                    <br/>
                    <br/>
                    <span className="instruction"> Количество ограничений  </span>
                    <select name="rows" className="input styled-select blue semi-square" value={this.state.rows}
                            onChange={this.handleChangeRows}>
                        <option value="3">2</option>
                        <option value="4">3</option>
                        <option value="5">4</option>
                        <option value="6">5</option>
                        <option value="7">6</option>
                        <option value="8">7</option>
                    </select>
                </form>
                <br/>
                <imaginary><ValuesDialog cols={this.state.cols} rows={this.state.rows}/></imaginary>
                <br/>
                <a href="/index.html#setValues" className="button"> <b className="boldText">{this.state.cols}</b>
                    переменных и <b className="boldText">{this.state.rows - 1}</b> ограничения</a>
                <br/>
            </div>
        );
    }
}

ReactDom.render(<App />, document.getElementById('react'));