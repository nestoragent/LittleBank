<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Little Bank</title>
    <script src="https://fb.me/react-15.0.1.js"></script>
    <script src="https://fb.me/react-dom-15.0.1.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/react/15.3.2/react.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.23/browser.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.3/toastr.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.3/toastr.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.3/toastr.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<div class='container'>
    <div id='root'></div>
</div>

<%--<script type="text/babel" src="<c:url value='/assets/js/displayAll.js' />">--%>
<script type="text/babel">

    var Row = React.createClass({

        getInitialState: function () {
            return {
                tempValue: this.props.value
            }
        },

        componentWillReceiveProps: function (nextProps) {
            //here u might want to check if u are currently editing but u get the idea -- maybe u want to reset it to the current prop on some cancelEdit method instead
            this.setState({
                tempValue: nextProps.value
            });
        },

        render: function () {
            return <div><input type="text" value={this.state.tempValue} onChange={this.onChange}/></div>;
        },

        onChange: function (e) {
            this.setState({
                tempValue: e.target.value
            });
        }
    });

    var BankAccount = React.createClass({
        handleUpdate() {
            const self = this;
            $.ajax({
                url: "/littlebank/update?" +
                "account_id=" + self.props.account.account_id +
                "accountNumber=" + self.props.account.accountNumber +
                "iban=" + self.props.account.iban +
                "bankName=" + self.props.account.bankName +
                "bic=" + self.props.account.bic,
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
                    <td><Row value={this.props.account.account_id}/></td>
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
                        <th>account_id</th>
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

    ReactDOM.render(
        <BankAccountTable accounts={${accounts}}/>, document.getElementById('root')
    );
</script>

</body>
</html>