'use strict';

var BankAccount = React.createClass({
    handleUpdate() {
        const self = this;
        $.ajax({
            url: "/littlebank/update?" +
            "accountNumber=" + self.props.account.accountNumber +
            "&IBAN=" + self.props.account.iban +
            "&bankName=" + self.props.account.bankName +
            "&bic=" + self.props.account.bic,
            type: 'POST',
            success: function (result) {
                toastr.info('Added task to the Queue.');
            },
            error: function (response, status, result) {
                toastr.options.timeOut = 0;
                toastr.error(result + " (Code: " + response.status + ")", "Error while updating!");
            }
        });
    },
    handleDelete() {
        const self = this;
        $.ajax({
            url: "/delete?accountNumber=" + self.props.account.accountNumber,
            success: function (result) {
                toastr.info('Added task to the Queue.');
            },
            error: function (response, status, result) {
                toastr.options.timeOut = 0;
                toastr.error(result + " (Code: " + response.status + ")", "Error while deleting!");
            }
        });
    },
    render: function () {
        return (
            <tr>
                <td>{this.props.account.accountNumber}</td>
                <td>{this.props.account.iban}</td>
                <td>{this.props.account.bankName}</td>
                <td>{this.props.account.bic}</td>
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
            rows.push(<BankAccount account={account} key={account.accountNumber}/>);
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
    }

    constructor() {
        super();
        this.state = {accounts: []};
    }

    createAccount(newAccount){
        $.ajax({
            url: "/create?accountNumber=" + newAccount.accountNumber +
            "&IBAN=" + newAccount.IBAN +
            "&bankName=" + newAccount.bankName +
            "&bic=" + newAccount.bic,
            type: 'POST',
            success: function (result) {
                toastr.info('Added task to the Queue.');
            },
            error: function (response, status, result) {
                toastr.options.timeOut = 0;
                toastr.error(result + " (Code: " + response.status + ")", "Error while updating!");
            }
        });
    }

    componentDidMount() {
        this.loadEmployeesFromServer();
    }

    render() {
        return (
            <div>
                <CreateDialog/>
                <BankAccountTable accounts={this.state.accounts}/>
            </div>
        );
    }
}

// tag::create-dialog[]
class CreateDialog extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            accountNumber: "",
            IBAN: "",
            bankName: "",
            bic: ""
        };
        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleInputChange(event) {
        const target = event.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const name = target.name;

        this.setState({
            [name]: value
        });
    }

    handleSubmit(event) {
        var stateNew = event.state;
        var newEmployee = {
            accountNumber: stateNew.accountNumber,
            IBAN: stateNew.IBAN,
            bankName: stateNew.bankName,
            bic: stateNew.bic
        };
        this.props.createAccount(newEmployee);
        //// Navigate away from the dialog to hide it.
        //window.location = "#";
    }

    render() {
        return (
            <div>
                <a href="#createAccount">Create</a>

                <div id="createAccount" className="modalDialog">
                    <div>
                        <a href="#" title="Close" className="close">X</a>

                        <h2>Create new bank account</h2>

                        <form>
                            <input type="text" name="accountNumber" placeholder="Account number" className="field"
                                   value={this.state.accountNumber} onChange={this.handleInputChange}/>
                            <input type="text" name="IBAN" placeholder="IBAN" className="field" value={this.state.IBAN}
                                   onChange={this.handleInputChange}/>
                            <input type="text" name="bankName" placeholder="Bank name" className="field"
                                   value={this.state.bankName} onChange={this.handleInputChange}/>
                            <input type="text" name="bic" placeholder="BIC" className="field" value={this.state.bic}
                                   onChange={this.handleInputChange}/>
                            <button onClick={this.handleSubmit}>Create</button>
                        </form>
                    </div>
                </div>
            </div>
        )
    }

}
// end::create-dialog[]

ReactDOM.render(
    <App/>, document.getElementById('root')
);