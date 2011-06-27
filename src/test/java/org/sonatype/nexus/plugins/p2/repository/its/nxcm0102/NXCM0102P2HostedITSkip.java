/**
 * Copyright (c) 2008-2011 Sonatype, Inc.
 *
 * All rights reserved. Includes the third-party code listed at http://www.sonatype.com/products/nexus/attributions.
 * Sonatype and Sonatype Nexus are trademarks of Sonatype, Inc. Apache Maven is a trademark of the Apache Foundation.
 * M2Eclipse is a trademark of the Eclipse Foundation. All other trademarks are the property of their respective owners.
 */
package org.sonatype.nexus.plugins.p2.repository.its.nxcm0102;

import java.io.File;

import org.apache.maven.index.artifact.Gav;
import org.junit.Assert;
import org.sonatype.nexus.plugins.p2.repository.its.AbstractNexusProxyP2IT;
import org.sonatype.nexus.test.utils.GavUtil;

public class NXCM0102P2HostedITSkip
    extends AbstractNexusProxyP2IT
{
    public NXCM0102P2HostedITSkip()
    {
        super( "nxcm0102" );
    }

    public void test()
        throws Exception
    {

        deploy( new File( getOverridableFile( "p2artifacts" ), "nexus-p2-its-bundle/pom.xml" ),
            "org.sonatype.nexus.plugins.it.p2.repository", // groupId
            "com.sonatype.nexus.p2.its.bundle", // artifactId
            "1.0.0", // version
            "pom" // packaging
        );

        deploy( new File( getOverridableFile( "p2artifacts" ),
            "nexus-p2-its-bundle/com.sonatype.nexus.p2.its.bundle_1.0.0.jar" ),
            "org.sonatype.nexus.plugins.it.p2.repository", // groupId
            "com.sonatype.nexus.p2.its.bundle", // artifactId
            "1.0.0", // version
            "jar" // packaging
        );

        // check if we can install it from p2shadow
        final String nexusTestRepoUrl = getNexusTestRepoUrl();

        final File installDir = new File( "target/eclipse/nxcm0102" );

        installUsingP2( nexusTestRepoUrl, "com.sonatype.nexus.p2.its.bundle", installDir.getCanonicalPath() );

        final File bundle = new File( installDir, "plugins/com.sonatype.nexus.p2.its.bundle_1.0.0.jar" );
        Assert.assertTrue( bundle.canRead() );
    }

    public void deploy( final File artifactFile, final String groupId, final String artifactId, final String version,
                        final String packaging )
        throws Exception
    {
        final Gav gav = GavUtil.newGav( groupId, artifactId, version, packaging );

        getDeployUtils().deployWithWagon( "http", getRepositoryUrl( "m2hosted" ), artifactFile,
            getRelitiveArtifactPath( gav ) );
    }

}