<#import "parts/common.ftl" as c>
<@c.page>
    <h3>${userChannel.getUsername()}</h3>
    <#if !isCurrent>
        <#if isSubscriber>
            <a class="btn btn-info my-3" href="/user/unsubscribe/${userChannel.id}">Unsubscribe</a>
        <#else>
            <a class="btn btn-info my-3" href="/user/subscribe/${userChannel.id}">Subscribe</a>
        </#if>
    </#if>

    <div class="container">
        <div class="row">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Subscriptions</div>
                        <h3 class="card-text">
                            <a href="/user/subscriptions/${userChannel.id}/list">${subscriptionCount}</a>
                        </h3>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Subscribers</div>
                        <h3 class="card-text">
                            <a href="/user/subscribers/${userChannel.id}/list">${subscribersCount}</a>
                        </h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <#if isCurrent>
        <#if message??>
            <#include "parts/messageEdit.ftl" />
        </#if>
    </#if>
    <#include "parts/messageList.ftl" />
</@c.page>