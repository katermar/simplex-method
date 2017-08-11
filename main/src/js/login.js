/**
 * Created by USER on 5/9/2017.
 */

'use strict';

var React = require('react');
var ReactDom = require('react-dom');

class Login extends React.Component {
    render() {
        return(
            // <imaginary><a></a></imaginary>
            <div id="error" className="errorDialog">
                <div>
                    <a href="#" title="Close" className="close">X</a>

                    <h2>Ошибка авторизации</h2>
                </div>
            </div>
        )
    }
}

ReactDom.render(<Login/>, document.getElementById("react"));