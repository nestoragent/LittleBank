'use strict';

// // tag::vars[]
// const React = require('react');
// const ReactDOM = require('react-dom');
// // end::vars[]

class Row extends React.Component {

    constructor(props) {
        super();
        this.state = {
            tempValue: props.value
        };
    }

    componentWillReceiveProps(nextProps) {
        //here u might want to check if u are currently editing but u get the idea -- maybe u want to reset it to the current prop on some cancelEdit method instead
        // this.setState({
        //     tempValue: nextProps.value
        // });
        this.state = {
            tempValue: nextProps.value
        };
        toastr.info('Set value: ' + nextProps.value);
    }

    onChange(e) {
        toastr.info('Set212 value: ' + e.target.value);
        this.setState({
            tempValue: e.target.value
        });
    }

    render() {
        return <div><input type="text" value={this.state.tempValue} onChange={this.onChange}/></div>;
    }


}

var BankAccount = React.createClass({
    handleUpdate() {
        const self = this;
        $.ajax({
            url: "/littlebank/update?" +
            "&account_id=" + self.props.account.account_id +
            "&accountNumber=" + self.props.account.accountNumber +
            "&IBAN=" + self.props.account.iban +
            "&bankName=" + self.props.account.bankName +
            "&bic=" + self.props.account.bic,
            type: 'POST',
            success: function (result) {
                toastr.info('Added task to the Queue.');
            },
            error: function (xhr, ajaxOptions, thrownError) {
                toastr.error("Something went wrong.");
            }
        });
    },
    handleDelete() {
        const self = this;
        $.ajax({
            url: "/littlebank/delete?account_id=" + self.props.account.account_id,
            type: 'DELETE',
            success: function (result) {
                toastr.info('Added task to the Queue.');
            },
            error: function (xhr, ajaxOptions, thrownError) {
                toastr.error("Something went wrong.");
            }
        });
    },
    render: function () {
        return (
            <tr>
                <td><Row value={this.props.account.accountNumber}/></td>
                <td><Row value={this.props.account.iban}/></td>
                <td><Row value={this.props.account.bankName}/></td>
                <td><Row value={this.props.account.bic}/></td>
                <td>
                    <button className="btn btn-info" onClick={this.handleUpdate}>Update</button>
                </td>
                <td>
                    <button className="btn btn-info" onClick={this.handleDelete}>Delete</button>
                </td>
            </tr>);
    }

});

var BankAccountTable = React.createClass({
    render: function () {
        var rows = [];
        this.props.accounts.forEach(function (account) {
            rows.push(<BankAccount account={account} key={account.account_id}/>);
        });
        return (
            <table className="table table-striped">
                <thead>
                <tr>
                    <th>accountNumber</th>
                    <th>IBAN</th>
                    <th>bankName</th>
                    <th>bic</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>{rows}</tbody>
            </table>);
    }
});

class App extends React.Component {

    loadEmployeesFromServer() {
        const self = this;
        $.ajax({
            url: "http://localhost:10000/api/bankAccounts"
        }).then(function (data) {
            self.setState({accounts: data._embedded.bankAccounts});
        });
    };

    constructor() {
        super();
        this.state = {accounts: []};
    }

    componentDidMount() {
        this.loadEmployeesFromServer();
    }

    render() {
        return (<BankAccountTable accounts={this.state.accounts}/>);
    }
}

ReactDOM.render(
    <App/>, document.getElementById('root')
);