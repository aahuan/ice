// **********************************************************************
//
// Copyright (c) 2002
// MutableRealms, Inc.
// Huntsville, AL, USA
//
// All Rights Reserved
//
// **********************************************************************

#ifndef ICE_SSL_ENDPOINT_H
#define ICE_SSL_ENDPOINT_H

#include <Ice/Endpoint.h>
#include <Ice/EndpointFactory.h>
#include <IceSSL/PluginBaseI.h>

namespace IceSSL
{

const ::Ice::Short SslEndpointType = 2;

class SslEndpoint : public IceInternal::Endpoint
{
public:

    SslEndpoint(const IceSSL::PluginBaseIPtr&, const std::string&, ::Ice::Int, ::Ice::Int);
    SslEndpoint(const IceSSL::PluginBaseIPtr&, const std::string&);
    SslEndpoint(const IceSSL::PluginBaseIPtr&, IceInternal::BasicStream*);

    virtual void streamWrite(IceInternal::BasicStream*) const;
    virtual std::string toString() const;
    virtual ::Ice::Short type() const;
    virtual ::Ice::Int timeout() const;
    virtual IceInternal::EndpointPtr timeout(::Ice::Int) const;
    virtual bool datagram() const;
    virtual bool secure() const;
    virtual bool unknown() const;
    virtual IceInternal::TransceiverPtr clientTransceiver() const;
    virtual IceInternal::TransceiverPtr serverTransceiver(IceInternal::EndpointPtr&) const;
    virtual IceInternal::ConnectorPtr connector() const;
    virtual IceInternal::AcceptorPtr acceptor(IceInternal::EndpointPtr&) const;
    virtual bool equivalent(const IceInternal::TransceiverPtr&) const;
    virtual bool equivalent(const IceInternal::AcceptorPtr&) const;

    virtual bool operator==(const IceInternal::Endpoint&) const;
    virtual bool operator!=(const IceInternal::Endpoint&) const;
    virtual bool operator<(const IceInternal::Endpoint&) const;

private:

    //
    // All members are const, because endpoints are immutable.
    //
    const IceSSL::PluginBaseIPtr _plugin;
    const std::string _host;
    const ::Ice::Int _port;
    const ::Ice::Int _timeout;
};

class SslEndpointFactory : public IceInternal::EndpointFactory
{
public:

    SslEndpointFactory(const IceSSL::PluginBaseIPtr&);
    virtual ~SslEndpointFactory();

    virtual ::Ice::Short type() const;
    virtual const ::std::string& protocol() const;
    virtual IceInternal::EndpointPtr create(const std::string&) const;
    virtual IceInternal::EndpointPtr read(IceInternal::BasicStream*) const;
    virtual void destroy();

private:

    IceSSL::PluginBaseIPtr _plugin;
};

}

#endif
