package org.jenkinsci.plugins.sonargerrit.review.notification;

import com.google.gerrit.extensions.api.changes.NotifyHandling;
import com.google.gerrit.extensions.api.changes.ReviewInput;
import org.jenkinsci.plugins.sonargerrit.config.NotificationConfig;
import org.junit.Assert;

/**
 * Project: Sonar-Gerrit Plugin
 * Author:  Tatiana Didik
 * Created: 18.11.2017 14:42
 * <p>
 * $Id$
 */
public class ReviewCommentedIssuesNotificationTest extends BaseNotificationTest {
    @Override
    public void initialize() {
        super.initialize();
        commentIssues.put("juice-bootstrap/src/main/java/com/turquoise/juice/bootstrap/plugins/PluginsManager.java", new DummyIssue());
    }

    protected NotifyHandling getDefault() {
        return NotificationConfig.DescriptorImpl.NOTIFICATION_RECIPIENT_COMMENTED_ISSUES;
    }

    protected void testNotification(NotifyHandling handling, NotifyHandling other) {
        publisher.getNotificationConfig().setNoIssuesNotificationRecipient(other.name());
        publisher.getNotificationConfig().setCommentedIssuesNotificationRecipient(handling.name());
        publisher.getNotificationConfig().setNegativeScoreNotificationRecipient(other.name());
        ReviewInput reviewResult = getReviewResult();
        Assert.assertEquals(handling, reviewResult.notify);
    }
}
