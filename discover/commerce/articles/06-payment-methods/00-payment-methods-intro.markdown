# Payment Methods [](id=payment-methods)

@commerce@ offers two payment methods---money order and purchase order---that do
not require the services of a third-party payment provider. Integration with
payment providers is also possible, with one---PayPal---ready to go out of the
box.

## Money Order [](id=money-order)

While there are obvious downsides to money orders---starting with the time it
takes to deliver payment through the mail---the payments are widely used in some
circles, and not necessarily only by low-tech firms. Follow these steps to offer
customers the option to pay by money order:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Settings* and click on the
    *Payment Methods* tab.

2.  Click *Money Order* and fill in the following fields in the form that
    displays:

    **Name**: Give the payment method a name, or leave it as "Money Order."

    **Description**: Text entered in this field appears on the screen where
    buyers select a payment method.

    **Image**: Hit *Browse* to upload or select an image to display along with
    the name and description.

    **Priority:** Set a priority to determine the order in which payment methods
    will be displayed on a page. Lower numbers come first.

    **Message**: Text entered here will be displayed after the customer chooses
    the money order option. This text could give instructions on where to send
    the money order, or provide information on when the product will ship, or
    the like.

3.  Check the *Active* Box and click *Save*.

## Purchase Order [](id=purchase-order)

Using a purchase order involves providing the buyer with an order form, and then
invoicing her after the form is submitted. Follow these steps to give buyers the
option to pay by purchase order:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Settings* and click on the
    *Payment Methods* tab.

2.  Click *Purchase Order* and fill in the following fields in the form that
    displays:

    **Name**: Give the payment method a name, or leave it as "Purchase Order."

    **Description**: Text entered in this field appears on the screen where
    buyers select a payment method.

    **Image**: Hit *Browse* to upload or select an image to display along with
    the name and description.

    **Priority:** Set a priority to determine the order in which payment methods
    will be displayed on a page. Lower numbers come first.

    **Message**: Text entered here will be displayed after the customer chooses
    the purchase order option. On the same page, the buyer will be prompted to
    enter a Purchase Order Number. Text entered in the message field could
    provide information such where to find the correct purchase order number,
    when to expect an invoice, or other like information.

3.  Check the *Active* Box and click *Save*.


+$$$

**Note:** This process does not create a mechanism for providing buyers with an
order form or for the buyer to submit that order form back to the seller. See
[Forms](/discover/portal/-/knowledge_base/7-0/basic-forms) and 
[Customer Portal](/web/liferay-emporio/documentation/-/knowledge_base/1-0/customer-portal)
for more on the submission of forms back and forth.

$$$

## PayPal [](id=paypal)

Follow these steps to give buyers the option to pay via PayPal:

1.  Go to the 
    [PayPal Developer Dashboard](https://developer.paypal.com/developer/applications/create)
    and obtain a Client ID and Client Secret. You will need to open an account and
    create a PayPal REST API application.

2.  Back in @Commerce@, go to *Site Menu* &rarr; *Commerce* &rarr; *Settings*
    and click on the *Payment Methods* tab.

3.  Click *PayPal* and fill in the following fields:

    **Name**: Give the payment method a name, or leave it as "PayPal."

    **Description:** Text entered in this field appears on the screen where
    buyers a select a payment method.

    **Image**: Hit *Browse* to upload or select an image to display along with
    the name and description.

    **Priority:** Set a priority to determine the order in which payment methods
    will be displayed on a page. Lower numbers come first.

4.  Enter the Client ID and Client Secret into the appropriate fields. Then
    select a *Mode* (*Sandbox* for testing, *Live* for actual transactions),
    check the *Active* box, and click *Save*
