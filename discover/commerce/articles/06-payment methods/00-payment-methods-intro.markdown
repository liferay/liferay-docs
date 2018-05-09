# Payment Methods [](id=payment-methods)

Out of the box, there are five different payment methods available.
Three--Authorize.net, Worldpay, and Paypal--require the services of a payment
processing company. The other two--money orders and purchase orders--can be set
up without involving a third party.

## Money Order [](id=money-order)

While there are obvious downsides to money orders--starting with the time it
takes to deliver payment through the mail--the payments are widely used in some
circles, and not necessarily only by low-tech firms.

+$$$

Additionally, because money orders are easy to set up, it is convenient to use
while testing other portions of the checkout process. See [link].

$$$

To set up money orders, go to the *Payment Methods* tab in *Commerce* &rarr;
*Settings*. Then click on *Money Order* to display the following fields:

**Name**: This field is already filled in by default, but it can be changed. The
name given here is displayed in the checkout where customers are prompted to
select a payment method--so don't call it "payment method no. 4" or something
else unhelpful.

**Description**: Text entered in this field appears on the screen where
customers select a payment method.

**Image**: Upload or select an image to display along with the name and
description.

**Message**: Text entered here will be displayed after the customer chooses the
money order option. It may make sense to put instructions here, such as "Please
send check or money order to Newcorp Order Processing, 43 Wallaby Way, Sydney."

## Purchase Order [](id=purchase-order)

Using a purchase order involves providing the buyer with an order form,
typically through a customer portal [link] and forms [link], and then invoicing
her after the form is submitted.

The checkout process is identical to that for money orders, except that the
customer will be prompted to enter a Purchase Order Number. Text entered in the
*Message* field will be displayed on the same page as the prompt.

## Authorize.net [](id=authorize-net)

To process credit card payments via Authorize.net, you need an API LoginID and
a transaction key from the processor itself. Once you have those--from
(surprise!) [authorize.net][https://www.authorize.net/]--you can enter them at
*Payment Methods* &rarr; *Authorize.net*, pick your *Environment*--*Production*
for actual transactions and *Sandbox* for testing) and set the method to active.

## Worldpay [](id=worldpay)

This method requires an Installation ID and a Service Key, which can be obtained
at [Worldpay][https://www.worldpay.com] and entered at *Payment Methods* &rarr;
*Worldpay*. You'll also need to choose from a wide range of payment methods--you
can read more about them on Worldpay's site.

## Paypal [](id=paypal)

You need a Client ID and Client Secret to set up Paypal. Get them from the
company's 
[Developer Dashboard][https://developer.paypal.com/developer/applications/create]
and enter them at *Payment Methods* &rarr; *Paypal*. Pick your *Mode*--*Live*
for actual transactions and *Sandbox* for testing--check the *Active* box and
you're ready to roll.
