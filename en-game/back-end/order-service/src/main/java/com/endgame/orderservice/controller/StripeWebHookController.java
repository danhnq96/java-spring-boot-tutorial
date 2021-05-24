package com.endgame.orderservice.controller;

import com.endgame.orderservice.config.StripeProperties;
import com.endgame.orderservice.entity.OrderStatus;
import com.endgame.orderservice.service.CheckoutService;
import com.google.gson.JsonSyntaxException;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.model.StripeObject;
import com.stripe.net.Webhook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/02/21
 * @project: order-service
 */
@RestController
@RequestMapping("/api/order/webhook/")
public class StripeWebHookController {
  private static final Logger logger = LoggerFactory.getLogger(StripeWebHookController.class);

  @Autowired
  private StripeProperties stripeProperties;

  @Autowired
  private CheckoutService checkoutService;

  public StripeWebHookController(StripeProperties stripeProperties) {
    this.stripeProperties = stripeProperties;
  }

  @PostMapping("/")
  public ResponseEntity<?> stripeWebhook(@RequestHeader("Stripe-Signature") String sigHeader,
                                         @RequestBody String payload) {
    logger.info("stripeWebhook - sigHeader: {} - payload: {}", sigHeader, payload);
    Event event = null;

    try {
      // If you are testing your webhook locally with the Stripe CLI you
      // can find the endpoint's secret by running `stripe listen`
      // Otherwise, find your endpoint's secret in your webhook settings in the Developer Dashboard
      String endpointSecret = stripeProperties.getWebHookSecret();
      event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
    } catch (JsonSyntaxException | SignatureVerificationException e) {
      // Invalid payload
      return (ResponseEntity<?>) ResponseEntity.badRequest();
    }

    // Deserialize the nested object inside the event
    EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
    StripeObject stripeObject = null;
    if (dataObjectDeserializer.getObject().isPresent()) {
      stripeObject = dataObjectDeserializer.getObject().get();
    } else {
      // Deserialization failed, probably due to an API version mismatch.
      // Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
      // instructions on how to handle this case, or return an error here.
    }

    // Handle the event
    switch (event.getType()) {
      case "payment_intent.succeeded":
        PaymentIntent paymentIntent = (PaymentIntent) stripeObject;
        // Then define and call a method to handle the successful payment intent.
        // handlePaymentIntentSucceeded(paymentIntent);
        checkoutService.updateOrderStatus(Long.parseLong(paymentIntent.getMetadata().get("orderId")),
          OrderStatus.CHARGED);
        break;
      case "payment_method.attached":
        PaymentMethod paymentMethod = (PaymentMethod) stripeObject;
        // Then define and call a method to handle the successful attachment of a PaymentMethod.
        // handlePaymentMethodAttached(paymentMethod);
        break;
      // ... handle other event types
      default:
        logger.info("Unhandled event type: " + event.getType());
    }

    return ResponseEntity.ok("");
  }
}
