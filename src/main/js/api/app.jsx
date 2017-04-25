import React from 'react';
import ReactDom from "react-dom";
import client from "./client";

class ValuesDialog extends React.Component {
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(e) {
        e.preventDefault();
        var updatedEmployee = {};
        this.props.attributes.forEach(attribute => {
            updatedEmployee[attribute] = ReactDOM.findDOMNode(this.refs[attribute]).value.trim();
        });
        this.props.onUpdate(this.props.employee, updatedEmployee);
        window.location = "#";
    }

    render() {
        var inputs = [];
        for (var i = 0; i < this.props.cols; i++) {
            inputs[i] = [];
            for (var j = 0; j < this.props.rows; j++) {
                inputs[i][j] = function () {
                    return (
                        <td key={i * 10 + j}>
                            <input type="text" className="value-field" placeholder={i * 10 + j} ref={i * 10 + j}/>
                        </td>
                    )
                }
            }
            inputs[i] = function () {
                return (
                    <tr>{inputs[i]}</tr>
                )
            }
        }

        var dialogId = "setValues";

        return (
            <div>
                <a href={"#" + dialogId}>setValues</a>

                <div id={dialogId} className="modalDialog">
                    <div>
                        <a href="#" title="Close" className="close">X</a>

                        <h2>setValues</h2>

                        <form>
                            <table>{inputs}</table>
                            <button onClick={this.handleSubmit}>NEXT</button>
                        </form>
                    </div>
                </div>
            </div>
        )
    }
}

class App extends React.Component {
    constructor() {
        super();
        this.state = {
            cols: '2',
            rows: '1'
        };
        this.handleChangeCols = this.handleChangeCols.bind(this);
        this.handleChangeRows = this.handleChangeRows.bind(this);
        // this.handleSubmit = this.handleSubmit.bind(this);
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

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <span class="instruction">Количество переменных</span>
                <select name="cols" class="input" value={this.state.cols} onChange={this.handleChangeCols}>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                </select>
                <br/>
                <span class="instruction">Количество ограничений</span>
                <select name="rows" class="input" value={this.state.rows} onChange={this.handleChangeRows}>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                </select>
                <button><ValuesDialog/></button>
            </form>
        );
    }
}

module.exports = ValuesDialog;

ReactDom.render(<ValuesDialog />, document.getElementById('react'));